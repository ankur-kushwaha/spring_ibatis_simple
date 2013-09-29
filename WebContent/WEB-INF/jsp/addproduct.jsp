<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form  modelAttribute="product" method="POST" action="addproduct.htm">
		<label for="idinput">ID: </label>
		<form:input path="id" id="idInput"></form:input>
		<form:errors path="id" cssclass="error"></form:errors>
		<br />
		<label for="priceInput">Price: </label>
		<form:input path="price" id="priceInput"></form:input>
		<form:errors path="price" cssclass="error"></form:errors>
		<br />
		<label for="descriptionInput">Description: </label>
		<form:input path="description" id="descriptionInput"></form:input>
		<form:errors path="description" cssclass="error"></form:errors>
		<br />
		<input type="submit" align="center" value="Execute">
	</form:form>
</body>
</html>