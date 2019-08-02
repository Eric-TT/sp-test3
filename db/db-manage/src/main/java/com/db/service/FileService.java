package com.db.service;

import org.springframework.web.multipart.MultipartFile;

import com.db.vo.EasyUI_Image;

public interface FileService {

	EasyUI_Image fileUpload(MultipartFile uploadFile);

}
