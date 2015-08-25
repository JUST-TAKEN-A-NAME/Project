package cn.hxp.common;

import cn.hxp.utils.JsonUtils;
import cn.hxp.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class FileHelper {

	private static final Logger logger = LoggerFactory.getLogger(FileHelper.class);
	
	
	/**
	 * 获取文件名(含后缀)
	 * 
	 * @param filePath 文件路径
	 * @return
	 */
	public static String getFileName(String filePath) {
		String name = "";
		if (filePath == null || filePath.trim().length() <= 0) {
			return name;
		}

		File file = new File(filePath);
		if (file != null && file.isFile()) {
			return file.getName();
		}

		int index = filePath.lastIndexOf("/");
		if (index > 0)
			filePath = filePath.substring(index + 1);

		index = filePath.lastIndexOf("\\");
		if (index > 0)
			filePath = filePath.substring(index + 1);

		return filePath;
	}
	
	
	
	
	public static FileItem getUploadFileItem(List<FileItem> list) {
		for (FileItem fileItem : list) {
			if(!fileItem.isFormField()) {
				return fileItem;
			}
		}
		return null;
	}
	
	
	public static String getUploadFileName(FileItem item) {
		// 获取路径名
		String value = item.getName();
		// 索引到最后一个反斜杠
		int start = value.lastIndexOf("/");
		// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
		String filename = value.substring(start + 1);
		
		return filename;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static void bolgUploadImg(HttpServletRequest request, HttpServletResponse response, String imgFolder, String bolgId){
		// 此map用户承载执行之后的结果
		Map<String,String> map = new HashMap<String,String>();
		
		if(StringUtils.checkIsEmpty(imgFolder) || StringUtils.checkIsEmpty(bolgId)){
			map.put("404", "网站内部错误！");
			return;
		}
		
		try {
			request.setCharacterEncoding("utf-8"); // 设置编码
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
			// 获得磁盘文件条目工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
			// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
			/**
			 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem
			 * 格式的 然后再将其真正写到 对应目录的硬盘上
			 */
			factory.setRepository(new File(GlobalConstants.GLOBAL_BOLG_IMG_FOLDER_TEMP));
			// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
			factory.setSizeThreshold(1024 * 1024);
			
			
			// 高水平的API文件上传处理
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//设置最大上传值
			upload.setFileSizeMax(1024 * 1024 * 3);
			
			// 提交上来的信息都在这个list里面
			// 这意味着可以上传多个文件
			// 请自行组织代码
			List<FileItem> list = upload.parseRequest(request);
			// 获取上传的文件
			FileItem item = getUploadFileItem(list);
			// 获取文件名
			String fileinfo = getUploadFileName(item);
			
			String fileType = fileinfo.substring(fileinfo.lastIndexOf(".")+1,fileinfo.length());
			
			//String filename = fileinfo.substring(0,fileinfo.lastIndexOf("."));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String filename = sdf.format(new Date());//用时间给图片命名
			
			
			System.out.println("存放目录:" + imgFolder);
			System.out.println("文件名:" + filename);
			
			
			String filename_base64 = Base64.encode(filename.getBytes())+"."+fileType;
			
			
			// 真正写到磁盘上
			item.write(new File(imgFolder, filename_base64)); // 第三方提供的

			//获取缓存图片文件夹的路径
			/*String basePath = request.getScheme()+"://"+request.getServerName()+":"
					+request.getServerPort()+request.getContextPath()+"/uploadImg/";*/
			
			
			// 插入图片，根据时间生成id唯一值，添加到session中 
			List<String> session_uploadImg_list = new ArrayList<String>();
			if(request.getSession().getAttribute("uploadImgIdList") != null){
				session_uploadImg_list = (List<String>) request.getSession().getAttribute("uploadImgIdList");
			}
			session_uploadImg_list.add(Base64.encode(filename.getBytes())+fileType);
			request.getSession().setAttribute("uploadImgIdList", session_uploadImg_list);

			
			// 插入成功，返回插入的图片路劲
			map.put("state", "666");
			map.put("imgpath", "/UploadImg/"+bolgId+"/"+filename_base64);
			map.put("id", Base64.encode(filename.getBytes())+fileType);
			
		} catch (FileSizeLimitExceededException e){
			map.put("state", "403");
			map.put("msg", "上传图片超过3m，请选择小于3m的图片");
			
		}  catch (FileUploadException e) {
			//commons.fileupload上传组件错误！
			map.put("state", "403");
			map.put("msg", "图片插入异常，请稍后再试！");
			
		} catch (Exception e) {
			//图片写入磁盘发生错误！
			map.put("state", "403");
			map.put("msg", "图片插入异常，请稍后再试！");
			
		} finally{
			
			JsonUtils.writeJson(response, map);
		}
	}
	
	
	//根据博客ID创建图片文件夹, 目的：根据ID命名文件夹来保存每篇博客中的图片
	public static String createFolderByBolgId(String bolgId){
		
		if(StringUtils.checkIsEmpty(bolgId)){
			return null;
		}
		bolgId = "/"+bolgId;
		File file =new File(GlobalConstants.GLOBAL_BOLG_IMG_FOLDER);
		//博客文件夹默认是存在的,不纯在即非正常情况，另当别论
		if(!file.exists()  && !file.isDirectory())      
		{
			//这里标识博客图片文件夹没找到，正常情况下是存在的，所以暂时不创建，提示用户上传图片错误
			return null;
		}
		
		File file2 = new File(GlobalConstants.GLOBAL_BOLG_IMG_FOLDER + bolgId);
		
		if(!file2.exists() && !file2.isDirectory()){
			file2.mkdir();
		}
		return file2.getPath();
	}
	
	
	
	
	/**
	 * 删除指定文件夹下所有文件
	 * @param param path 文件夹完整绝对路径
	 */
	public static boolean delAllFile(String path) {
		
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + File.separator + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 删除某个文件夹包括里面的文件
	 * @param folderPath
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void delFile(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
		
	}
	
}
