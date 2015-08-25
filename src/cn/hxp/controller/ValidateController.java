package cn.hxp.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hxp.common.ValidateHelper;

import cn.hxp.utils.PatchcaUtils;



@Controller
@RequestMapping("/validate")
public class ValidateController {
	
	@RequestMapping("getValidateCodeImage")
	public void getValidateCodeImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("image/" + PatchcaUtils.DEFAULT_VALIDATE_IMAGE_TYPE);
		response.setHeader("cache", "no-cache");
		
		OutputStream outputStream = response.getOutputStream();

		try {
			ValidateHelper.setImageValidateCode(request.getSession(), PatchcaUtils.generateCptcha(outputStream));
		} catch (IOException e) {
			//logger.info("*******************Exception: " , e);
			//throw e;
		}
	}
}
