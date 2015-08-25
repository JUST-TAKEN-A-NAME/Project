package cn.hxp.common;

import cn.hxp.utils.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadHelper {

	// 日志处理
	private static final Logger logger = LoggerFactory.getLogger(UploadHelper.class);

	private static final String DEFAULT_UPLOAD_FILE_NAME = "Filedata";

	public static final String FILE_SUFFIX_TYPE_XLS = "xls";

	public static final String FILE_SUFFIX_TYPE_XLSX = "xlsx";

	public static final String FILE_SUFFIX_TYPE_CSV = "csv";

	public static final String FILE_SUFFIX_TYPE_TXT = "txt";

	public static InputStream getInputStream(HttpServletRequest request) throws Exception {
		FileItem fileItem = getFileItem(request, "UTF-8");

		return fileItem == null ? null : fileItem.getInputStream();
	}

	public static FileItem getFileItem(HttpServletRequest request) throws Exception {
		return getFileItem(request, "UTF-8");
	}

	public static FileItem getFileItem(HttpServletRequest request, String encoding) throws Exception {

		if (request == null) {
			return null;
		}

		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding(encoding);

		@SuppressWarnings("unchecked")
		List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		Iterator<FileItem> iter = items.iterator();

		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();

			if (item.isFormField()) {
				fields.put(item.getFieldName(), item.getString());
				//为了在上传文件时取出非File字段的值2013-06-05
				request.getSession().setAttribute(item.getFieldName(), item.getString());
			} else {
				fields.put(item.getFieldName(), item);
			}
		}

		FileItem uploadFile = (FileItem) fields.get(DEFAULT_UPLOAD_FILE_NAME);

		return uploadFile;

		// //////////////////////////////////////////////////////////////////////
		// // springmvc实现文件上传，暂时保存，以备不时之需
		// MultipartHttpServletRequest multipartRequest =
		// (MultipartHttpServletRequest) request;
		//
		// // 上传文件名
		// MultipartFile mf = multipartRequest.getFile("Filedata");
		// String fileName = multipartRequest.getParameter("fileName");
		//
		// File uploadFile = new File(ctxPath + fileName);
		// FileCopyUtils.copy(mf.getBytes(), uploadFile);
		// //////////////////////////////////////////////////////////////////////
	}

	public static File saveFile(HttpServletRequest request, String path, String encoding) throws Exception {
		if (request == null || StringUtils.checkIsEmpty(path)) {
			return null;
		}

		String savePath = path.endsWith("/") ? path : path + "/";

		FileItem uploadFile = null;
		if (StringUtils.checkIsEmpty(encoding)) {
			uploadFile = getFileItem(request);
		} else {
			uploadFile = getFileItem(request, encoding);
		}

		String fileName = new String(uploadFile.getName());

		fileName = FileHelper.getFileName(fileName);

		File file = new File(savePath);

		if (!file.exists()) {
			file.mkdirs();
		}

		File saveFile = new File(savePath, fileName);
		uploadFile.write(saveFile);

		return saveFile;
	}

	public static File saveFile(HttpServletRequest request, String path) throws Exception {

		if (request == null || StringUtils.checkIsEmpty(path)) {
			return null;
		}

		String savePath = path.endsWith("/") ? path : path + "/";
		FileItem uploadFile = getFileItem(request);
		String fileName = new String(uploadFile.getName());

		fileName = FileHelper.getFileName(fileName);

		File file = new File(savePath);

		if (!file.exists()) {
			file.mkdirs();
		}

		File saveFile = new File(savePath, fileName);
		uploadFile.write(saveFile);

		return saveFile;

	}

	public static List<File> saveMultipartFile(HttpServletRequest request, String path) throws Exception {

		if (request == null || StringUtils.checkIsEmpty(path)) {
			return null;
		}

		List<File> fileList = new ArrayList<File>();
		String savePath = path.endsWith("/") ? path : path + "/";

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = (Map<String, MultipartFile>) multipartRequest.getFileMap();

		Iterator<Entry<String, MultipartFile>> iter = fileMap.entrySet().iterator();

		String fileName;
		CommonsMultipartFile multipartFile;
		while (iter.hasNext()) {
			Entry<String, MultipartFile> entry = iter.next();
			multipartFile = (CommonsMultipartFile) entry.getValue();

			FileItem uploadFile = multipartFile.getFileItem();
			fileName = new String(uploadFile.getName());

			fileName = FileHelper.getFileName(fileName);

			File file = new File(savePath);

			if (!file.exists()) {
				file.mkdirs();
			}

			File saveFile = new File(savePath, fileName);
			uploadFile.write(saveFile);
			fileList.add(saveFile);

		}

		return fileList;

	}

	
	/**
	 * 删除单个文件
	 * 
	 * @param sPath 被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
	
}
