package com.db.controller;

import com.db.service.FileService;
import com.db.vo.EasyUI_Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/file")
    public String file(MultipartFile fileImage) throws IllegalStateException, IOException {
        String fileName = fileImage.getOriginalFilename();
        String filePath = "E:/db/images";
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File file = new File(filePath + "/" + fileName);
        fileImage.transferTo(file);
        return "redirect:/file.jsp";
    }

    /**
     * 实现用户文件上传
     */
    @RequestMapping("/pic/upload")
    @ResponseBody
    public EasyUI_Image fileUpload(MultipartFile uploadFile) {
        return fileService.fileUpload(uploadFile);
    }
}
