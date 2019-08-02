package com.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "db_content")
@EqualsAndHashCode(callSuper = false)
public class Content extends BasePojo {
	private static final long serialVersionUID = -2861655810896945802L;
	@TableId(type = IdType.AUTO)
	private Long id;
	private Long categoryId;// 内容类目ID
	private String title;// 内容标题
	private String subTitle; // 子标题
	private String titleDesc;// 标题描述
	private String url;// 链接
	private String pic;// 图片绝对路径
	private String pic2;// 图片绝对路径
	private String content;// 内容
}
