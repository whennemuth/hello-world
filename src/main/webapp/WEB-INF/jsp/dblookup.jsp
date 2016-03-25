<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
<c:out value="${db.comment}"></c:out>
<br>
<form action="lookup.htm" method="post">
<textarea name="sql" cols=80 rows=30></textarea>
</form>
<br>
<input type="button" value="submit" onclick="document.forms[0].submit();">
<%@ include file="footer.jsp" %>