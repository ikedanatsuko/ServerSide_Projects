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
<title>${todoList.title}</title>
</head>
<body>
	<center>
		<h1>${todoList.title}</h1>
		<h2>${message}</h2>

		<a href="/TodolistApp/">Top</a>

		<table>
			<f:form modelAttribute="todo" action="todo/create" method="post">
				<tr>
					<td><input type="text" id="detail" name="detail"></td>
					<td><input type="submit" value="create"></td>
				</tr>
				<tr><f:errors path="detail" element="p" style="color:red" /></tr>
			</f:form>
			<c:forEach items="${todosByList}" var="currentTodo">
				<tr>
					<td>${currentTodo.detail}</td>
					<td><c:if test="${currentTodo.done}">
						Done!
					</c:if> <c:if test="${not currentTodo.done}">
							<f:form modelAttribute="finishTodo"
								action="todo/${currentTodo.id}/finish" method="post">
								<f:hidden path="id" value="${fn:escapeXml(currentTodo.id)}" />
								<input type="submit" name="finish" value="finish">
							</f:form>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>