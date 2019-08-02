package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUI_Image {
	private Integer error=0;
	private String url;//图片的虚拟路径
	private Integer height;
	private Integer weight;
	//多系统之间直接传递时必须序列化
	
}
