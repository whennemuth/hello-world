<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body leftmargin="50" topmargin=50>
	<div class="wrapper">
		<div class="header">Hello World!</div>
		<div class="side">
			<a href="hello.htm?task=config">Show config file</a>
			<br><br>
			<a href="hello.htm?task=lookup&dbtype=MYSQL">Database query (mysql local)</a>
			<br><br>
			<a href="hello.htm?task=lookup&dbtype=MYSQL_DOCKER">Database query (mysql docker)</a>
			<br><br>
			<a href="hello.htm?task=lookup&dbtype=MYSQL_LOCAL_DOCKER">Database query (mysql local docker)</a>
			<br><br>
			<a href="hello.htm?task=lookup&dbtype=ORACLE">Database query (oracle local)</a>
			<br><br>
			<a href="hello.htm?task=lookup&dbtype=ORACLE_DOCKER">Database query (oracle docker)</a>
		</div>
		<div class="body">