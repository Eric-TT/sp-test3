<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-Control" content="max-age=300" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${query} - 商品搜索 - 东宝</title>
<meta name="Keywords" content="java,东宝java" />
<meta name="description" content="在东宝中找到了29910件java的类似商品，其中包含了“图书”，“电子书”，“教育音像”，“骑行运动”等类型的java的商品。" />
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/psearch20131008.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/psearch.onebox.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/pop_compare.css" media="all" />
<script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
</head>
<script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=你的密钥"></script>
<!-- header start -->
<jsp:include page="../commons/header.jsp" />

<script type="text/javascript">
 
    //百度地图API功能
    var map = new BMap.Map("allmap");
    map.centerAndZoom(new BMap.Point(120.378386,30.309756),15);//根据坐标初始化地图
     map.enableScrollWheelZoom(true);
    map.addControl(new BMap.NavigationControl());   //平移缩放控件
    map.addControl(new BMap.ScaleControl());        //比例尺
    map.addControl(new BMap.OverviewMapControl());  //缩略地图
    map.addControl(new BMap.MapTypeControl());      //地图类型
    map.setCurrentCity("杭州"); // 仅当设置城市信息时，MapTypeControl的切换功能才能可用
 
    //添加标注
    var marker = new BMap.Marker(new BMap.Point(120.378386,30.309756));        // 创建标注
    map.addOverlay(marker);                     // 将标注添加到地图中
</script>

<body>

<!-- 人员名单 -->
<div class="w main">
	<div class="crumb"><h1>全部人员:</h1>&nbsp;&nbsp;</div>
<table id="showTime" style="margin:0 auto;font-size: 24px;line-height:10px; font-weight: 900;color: #000;" cellpadding="20px">
		<tr>
			<td>组 长:</td>
			<td>彭海</td>
		</tr>
		<tr>
			<td>后 台 管 理 :</td>
			<td>黃明伟&nbsp;&nbsp;何毅</td>
		</tr>
		<tr>
			<td>购 物 车 管 理 :</td>
			<td>赖小涛&nbsp;&nbsp;龙洋</td>
		</tr>
		<tr>
			<td>订 单 管 理 : </td>
			<td>王 成 友&nbsp;&nbsp;曾俊超</td>
		</tr>
		<tr>
			<td>订单 维 护 :</td>
			<td>王 宗 庆&nbsp;吴涛&nbsp;唐洋洋</td>
		</tr>
		<tr>
			<td>首 页 及 登 陆 : </td>
			<td>彭豪&nbsp;&nbsp;陈星睿&nbsp;&nbsp;曾绍海</td>
		</tr>
		<tr>
			<td>前 端 : </td>
			<td>谢治东</td>
		</tr>

</table>
</div>
<div class="w" clstag="homepage|keycount|home2013|38a" style="margin-top: 50px;">
	<jsp:include page="../commons/footer.jsp" />
</div>
<!-- header end -->
</body>
</html>