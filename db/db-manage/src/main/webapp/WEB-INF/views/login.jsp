<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
<jsp:include page="/commons/common-js.jsp"></jsp:include>
</head>
<body style="background-color: #F3F3F3">
	<div class="easyui-dialog" title="管理员登录"
		data-options="closable:false,draggable:false"
		style="width: 400px; height: 300px; padding: 10px;">
		<div style="margin-left: 50px; margin-top: 50px;">
			<div style="margin-bottom: 20px;">
				<div>
					用户名: <input name="username" class="easyui-textbox"
						data-options="required:true" style="width: 200px; height: 32px"
						value="" />
				</div>
			</div>
			<div style="margin-bottom: 20px">
				<div>
					密&nbsp;&nbsp;&nbsp;码: <input name="password" class="easyui-textbox"
						type="password" style="width: 200px; height: 32px" data-options=""
						value="" />
				</div>
			</div>
			<div style="margin-bottom: 20px">
			<div class="checkbox icheck">
				<label> <input type="checkbox" id="rememberId" style="width: 50px; height: 15px">
					Remember Me
				</label>
			</div>
		</div>
			<div>
				<a id="login" class="easyui-linkbutton" iconCls="icon-ok"
					style="width: 100px; height: 32px; margin-left: 50px">登录</a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$("#login").click(function() {
			var username = $("[name=username]").val();
			var password = $("[name=password]").val();
			$.ajax({
				type : "post",
				url : "/doLogin",
				data :　{"username":username,
						"password":password},
				dataType : "json",
				success : function(data){
					if(data.status == 200){
						alert("登陆成功!!!!");
						location="http://manage.db.com";
					}else{
						alert("登陆失败请重试！！！")
						location="http://manage.db.com/doLoginUI";
					}
				}
			})
		});
	</script>
</body>
</html>