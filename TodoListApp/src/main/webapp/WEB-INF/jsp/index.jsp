<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="utf-8">
<link
	href="<c:url value="${pageContext.request.contextPath}/resources/css/test.css" />"
	rel="stylesheet">
<title>TodoList</title>
</head>
<body>
	<center>
		<h1>TodoLists</h1>

		<a href="/TodolistApp/search">検索</a> <a href="/TodolistApp/encomium">褒め言葉</a>

		<table>
			<f:form modelAttribute="todoList" action="todolist/create"
				method="post">
				<tr>
					<td><input type="text" id="title" name="title"></td>
					<td><input type="submit" value="create"></td>
				</tr>
				<tr>
					<f:errors path="title" element="p" style="color:red" />
				</tr>
			</f:form>
			<c:forEach items="${allTodolist}" var="currentTodolist">
				<tr>
					<td><a href="/TodolistApp/todolist/${currentTodolist.id}/edit">${currentTodolist.title}</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</center>

</body>
</html>
