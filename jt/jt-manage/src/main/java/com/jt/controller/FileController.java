package com.jt.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.service.FileService;
import com.jt.vo.EasyUI_Image;

@Controller
public class FileController {
	/*
	 * http://localhost:8091/file
	 */	
	@Autowired
	private FileService fileService;
	@RequestMapping("/file")
	public String file(MultipartFile fileImage) throws Exception {
		//实现文件上传API
		String fileName=fileImage.getOriginalFilename();
		File fileDir=new File("D:/jt-images/");
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		File file=new File("D:/jt-images/"+fileName);
		fileImage.transferTo(file);
		//要求重定向到file.jsp
		return "redirect:/file.jsp";
	}
	
	/*
	 * 实现文件上传的规则
	 */
	@RequestMapping("/pic/upload")
	@ResponseBody		//将数据转化为json
	public EasyUI_Image fileUpload(MultipartFile uploadFile) {
		return fileService.fileUpload(uploadFile);
	}
}
