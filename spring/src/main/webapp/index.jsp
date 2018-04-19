<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>

<script type="text/javascript" src="static/jquery/jquery-3.3.1.min.js"></script>

<style type="text/css">
.mr20 {margin-right: 20px;}
</style>
</head>
<body>

<a href="aop/list" class="mr20">用户列表信息</a>
<a href="aop/get/100" class="mr20">获取指定用户信息，并跳转页面</a>
<a href="aop/add" class="mr20">添加</a>
<br/>
<br/>
<br/>
<a href="aop/addUserInfo" class="mr20">添加用户信息-不分情况</a>
<a href="aop/addUserInfo/0" class="mr20">添加用户信息-情况1</a>
<a href="aop/addUserInfo/1" class="mr20">添加用户信息-情况2</a>
<br/>
<br/>
<br/>
<h1> AOP 参数，返回值验证 </h1>
<a href="aopParams/get/102" class="mr20">查询102</a>
<a href="aopParams/add?userId=200&userName=name200" class="mr20">添加200</a>
<a href="#" onclick="index.add(300)" class="mr20">添加300</a>


</body>

<script type="text/javascript">
	var index = {
			add : function(val) {
				var data = {
						userId : val,
						userName : "name" + val
				};
				
				$.ajax({
					url : "aopParams/add",
					contentType : "application/json",
					data : JSON.stringify(data),
					dataType : "json",
					method : "post",
					async : false,
					cache : false,
					beforeSend : function() {
						alert("before");
					},
					complete : function() {
						alert("complete");
					},
					error : function() {
						alert("error");
					},
					success : function(obj) {
						alert(obj);
					}
				});
			}
	};
</script>
</html>