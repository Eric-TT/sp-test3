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
@EqualsAndHashCode(callSuper = false)
@TableName("db_item_param_item")
public class ItemParamItem extends BasePojo {
	private static final long serialVersionUID = -5745651412633668041L;
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer itemId;
	private String paramData;
}
