package com.db.pojo;

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
@TableName("db_item_desc")
@EqualsAndHashCode(callSuper = false)
public class ItemDesc extends BasePojo {
	private static final long serialVersionUID = -3532471496881435303L;
	@TableId
	private Long itemId;
	private String itemDesc;
}
