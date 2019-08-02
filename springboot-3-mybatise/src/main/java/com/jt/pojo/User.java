package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor //添加全部构造
@NoArgsConstructor //添加无参构造
@TableName //将对象与表一一映射
public class User {
	/*
	 * 规则:
	 * 1.如果表明与对象名一致可以省略
	 * 2.如果表中字段与属性名称一致可以省略配置
	 */
	@TableId(type =IdType.AUTO )
	private Integer id;
	private String name;
	private Integer age;
	private String sex;
	
}
