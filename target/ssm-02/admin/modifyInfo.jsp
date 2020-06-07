<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	
	function submitData(){
		var nickName=$("#nickName").val();
		var sign=$("#sign").val();
		var proFile=UE.getEditor('proFile').getContent();
		
		if(nickName==null || nickName==''){
			alert("请输入昵称！");
		}else if(sign==null || sign==''){
			alert("请输入个性签名！");
		}
		else{
			$('#form1').submit();
		}
	}
	
	
	
</script>
</head>
<body style="margin: 10px">
<div id="p" class="easyui-panel" title="修改个人信息" style="padding: 10px">
	<form id="form1" action="${pageContext.request.contextPath}/admin/blogger/save" method="post" enctype="multipart/form-data">

	 	<table cellspacing="20px">
	   		<tr>
	   			<td width="80px">用户名：</td>
	   			<td>
	   				<input type="hidden" id="id" name="id" value="${sessionScope.loginBlogger.id }"/>
	   				<input type="text" id="userName" name="name" style="width: 200px;" value="${sessionScope.loginBlogger.name }" readonly="readonly"/>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td>昵称：</td>
	   			<td><input type="text" id="nickName" name="nickName" value="${sessionScope.loginBlogger.nickName}" style="width: 200px;"/></td>
	   		</tr>
	   		<tr>
	   			<td>个性签名：</td>
	   			<td><input type="text" id="sign" name="sign" value="${sessionScope.loginBlogger.sign }" style="width: 400px;"/></td>
	   		</tr>
	   		<tr>
	   			<td>个人头像：</td>
	   			<td><input type="file" id="imageFile" name="imageFile" style="width: 400px;"/></td>
	   		</tr>
			<tr>
				<td>邮箱：</td>
				<td><input type="text" id="email" name="email" value="${sessionScope.loginBlogger.email }" style="width: 400px;"/><span style="color: red;">${requestScope.errors.email}</span></td>
			</tr>
			<tr>
				<td>电话号码：</td>
				<td><input type="phone" id="sign" name="phone" value="${sessionScope.loginBlogger.phone }" style="width: 400px;"/><span style="color: red;">${requestScope.errors.phone}</span></td>
			</tr>
			<tr>
	   		<tr>
	   			<td></td>
	   			<td>
				<button class="easyui-linkbutton" >提交</button>
	   			</td>
	   		</tr>
	   	</table>
   	</form>
 </div>
</body>
</html>