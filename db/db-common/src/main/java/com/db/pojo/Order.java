package com.db.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@TableName("db_order")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Order extends BasePojo {
	private static final long serialVersionUID = 2479778235154508795L;
	@TableField(exist = false)
	private OrderShipping orderShipping;
	@TableField(exist = false)
	private List<OrderItem> orderItems;

	@TableId
	private String orderId;
	private String payment;
	private Integer paymentType;
	private String postFee;
	private Integer status;
	private Date paymentTime;
	private Date consignTime;
	private Date endTime;
	private Date closeTime;
	private String shippingName;
	private String shippingCode;
	private Long userId;
	private String buyerMessage;
	private String buyerNick;
	private Integer buyerRate;

}