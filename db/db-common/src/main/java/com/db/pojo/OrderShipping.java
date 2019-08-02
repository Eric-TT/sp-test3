package com.db.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@TableName("db_order_shipping")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class OrderShipping extends BasePojo {
	private static final long serialVersionUID = 4983054600656171142L;

	@TableId
	private String orderId;

	private String receiverName;

	private String receiverPhone;

	private String receiverMobile;

	private String receiverState;

	private String receiverCity;

	private String receiverDistrict;

	private String receiverAddress;

	private String receiverZip;

}