package com.jt.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.vo.EasyUI_Image;

@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService {
	/*
	 * 由于成员变量将路径写死,扩展不易,最好的方式应该写到配置文件中
	 */
	@Value("${image.localDirPath}")
	private String localDirPath;//="D:/jt-images/";
	@Value("${image.urlDirPath}")
	private String urlDirPath;//="http://image.jt.com/";

	/*
	 * 问题1.校验文件类型是否为图片 如何校验
	 * 问题2.如何防止恶意的文件上传
	 * 问题3.众多图片如何保存
	 * 问题4.文件如果重名如何处理
	 * 文件上传思路:
	 * 1.获取用户文件名称用户校验
	 * 2.检验文件名称是否为图片
	 * 3.利用工具API校验图片的宽度和高度
	 * 4.以时间格式创建文件夹保存数据 yyyy/MM/dd
	 * 5.判断文件是否存在 不存在新建文件目录
	 * 6.采用UUID为文件名称,防止文件重名 32位16进制数
	 */
	@Override
	public EasyUI_Image fileUpload(MultipartFile uploadFile) {
		EasyUI_Image ui_Image=new EasyUI_Image();
		//1.获取文件名称 abc.jpg
		String fileName=uploadFile.getOriginalFilename();
		//2.校验文件名称,正则表达式
		fileName=fileName.toLowerCase();//将字符转换为小写
		if(!fileName.matches("^.+\\.(jpg|png|gif)$")) {
			ui_Image.setError(1);
			return ui_Image;
		}
		//3.利用API读取用户提交的数据
		try {
			BufferedImage bufferedImage=ImageIO.read(uploadFile.getInputStream());
			int height = bufferedImage.getHeight();
			int width = bufferedImage.getWidth();
			if (height==0||width==0) {
				ui_Image.setError(1);
				return ui_Image;
			}
			ui_Image.setHeight(height).setWeight(width);//封装图片的数据
			//4.以时间格式创建文件夹
			String datePathDir=new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			String realDirPath=localDirPath+datePathDir;
			File dirFile = new File(realDirPath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			//6.采用UUID命名文件名称替换中间的-
			String uuid=UUID.randomUUID().toString().replace("-", "");
			//截串含头不含尾
			String fileType=fileName.substring(fileName.lastIndexOf("."));
			String realName=uuid+fileType;
			//7.实现文件的上传
			String realFilePath=realDirPath+"/"+realName;
			uploadFile.transferTo(new File(realFilePath));
			System.out.println("文件上传成功");
			//8.编辑虚拟路径数据返回
			String realUrlPath=urlDirPath+datePathDir+"/"+realName;
			ui_Image.setUrl(realUrlPath);
		} catch (Exception e) {
			e.printStackTrace();
			ui_Image.setError(1);//对象转换异常
			return ui_Image;
		}
		
		return ui_Image;
	}

}
