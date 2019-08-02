<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>
<script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
</head>
<style>
#myorder {
	font: xxx;
	font-size: 15px;
	background: write;
	margin-left: 5%;
	margin-right: 5%;
	margin-top: 3%;
	margin-bottom: 3%;
	width: 20%;
	height: 15px；
}
</style>
<body>
	<table id="myorder">
		<thead>
			<th>订单详情</th>
		</thead>
		<tbody>
			<tr>
				<td>订单编号：</td>
				<td>${order.orderId}</td>
			</tr>
			<tr>
				<td>下单时间：</td>
				<td>${order.created.toLocaleString()}</td>
			</tr>
			<tr>
				<td>预计到货时间：</td>
				<td>${reachTime.toLocaleString()}</td>
			</tr>
			<tr>
				<td>收货人：</td>
				<td>${order.orderShipping.receiverName}</td>
			</tr>
			<tr>
				<td>收获地址：</td>
				<td>${order.orderShipping.receiverAddress}</td>
			</tr>
			<tr>
				<td><input type="button" value="点击催单" onclick="quickly()" /></td>
			</tr>
			<tr>
				<td><input type="button" value="返回我的订单" onclick="backMyOrder()" /></td>
			</tr>
		</tbody>
	</table>
</body>
<script type="text/javascript">
	

		function backMyOrder() {

			window.location.href = "http://www.db.com/order/myOrder.html";
		}
		function quickly() {
			alert("您的订单正在快马加鞭地赶来，请耐心等待！！！")
		}
	
</script>
</html>