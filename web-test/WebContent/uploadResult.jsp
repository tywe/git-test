<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>   
<%@ taglib prefix="s" uri="/struts-tags" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
<%=basePath %>
username: <s:property value="username"/><br>

password: <s:property value="password"/><br>

file: <s:property value="fileFileName"/>


<s:iterator id="imgUrl" value="dataUrl">
    <br /><img src="${imgUrl}"/>
</s:iterator>
</body>
</html>