package com.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "db_content_category")
@EqualsAndHashCode(callSuper = false)
public class ContentCategory extends BasePojo {
	private static final long serialVersionUID = 5915121287677650430L;
	@TableId(type = IdType.AUTO)
	private Long id;// 类目ID
	private Long parentId;// 父类目ID=0时，代表的是一级的类目
	private String name;// 分类名称
	private Integer status;// 状态。可选值:1(正常),2(删除)
	private Integer sortOrder;// 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
	private Boolean isParent;// 该类目是否为父类目，1为true，0为false
}
