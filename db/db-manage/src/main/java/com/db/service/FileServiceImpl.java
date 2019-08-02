package com.db.service;

import com.db.vo.EasyUI_Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService {

	@Value("${image.localDirPath}")
	private String localDirPath;
	@Value("${image.urlDirPath}")
	private String urlDirPath;

	@Override
	public EasyUI_Image fileUpload(MultipartFile uploadFile) {
		EasyUI_Image easyUI_Image = new EasyUI_Image();
		String fileName = uploadFile.getOriginalFilename();
		fileName = fileName.toLowerCase();
		if (!fileName.matches("^.+\\.(jpg|png|gif)$")) {
			easyUI_Image.setError(1);
			return easyUI_Image;
		}
		try {
			BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			if (width == 0 || height == 0) {
				easyUI_Image.setError(1);
				return easyUI_Image;
			}
			easyUI_Image.setWidth(width).setHeight(height);
			String datePathDir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			String realDirPath = localDirPath + datePathDir;
			File dirFile = new File(realDirPath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			String realName = uuid + fileType;
			String realUrl = realDirPath + "/" + realName;
			File file = new File(realUrl);
			uploadFile.transferTo(file);
			String url = urlDirPath + datePathDir + "/" + realName;
			easyUI_Image.setUrl(url);
		} catch (IOException e) {
			e.printStackTrace();
			easyUI_Image.setError(1);
			return easyUI_Image;
		}
		return easyUI_Image;
	}

}
