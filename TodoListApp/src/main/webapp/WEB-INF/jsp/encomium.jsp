<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Encomium setup</title>
</head>
<body>
	<center>
		<h1>Encomium setup</h1>

		<a href="/TodolistApp/">Top</a>

		<table>
			<f:form modelAttribute="encomium" action="encomium/create"
				method="post">
				<tr>
					<td><input type="text" id="message" name="message"></td>
					<td><input type="submit" value="create"></td>
				</tr>
				<tr><f:errors path="message" element="p" style="color:red" /></tr>
			</f:form>
			<c:forEach items="${allEncomium}" var="currentEncomium">
				<tr>
					<td>${currentEncomium.message}</td>
				</tr>
			</c:forEach>
		</table>
	</center>

</body>
</html>