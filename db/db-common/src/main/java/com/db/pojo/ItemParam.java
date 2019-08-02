package com.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("db_item_param")
@EqualsAndHashCode(callSuper = false)
public class ItemParam extends BasePojo {
	private static final long serialVersionUID = 2123533922125802473L;
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer itemCatId;
	private String itemCatName;
	private String paramData;
}
