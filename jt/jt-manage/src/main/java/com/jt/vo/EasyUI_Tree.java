package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//vo是服务器与页面交互的对象 一般需要转换为json格式
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUI_Tree {
	private Long id;
	private String text;
	private String state;
	
}
