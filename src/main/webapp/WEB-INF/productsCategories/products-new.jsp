<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> <!--important line to display jstl form -->    
<%@ page contentType="text/html; charset=UTF-8" %>  <!--important line to display emojis-->
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href='<c:url value="/css/style.css"/>' type="text/css"/>
	<title></title>
</head>
<body>
	<h1>New Product</h1>
	<!--FORM----------------------------------------- -->
	<form:form action="/products/new/action" method="POST" modelAttribute="product" class="mx-auto">
		<p>
			<form:label path="name">Name</form:label>
			<form:errors path="name"/>
			<form:input path="name" class="form-control max-width"/>
		</p>
		<p>
			<form:label path="description">Description</form:label>
		<form:errors path="description"/>
			<form:input path="description" class="form-control max-width"/>
		</p>
		<p>
			<form:label path="price">Price</form:label>
		<form:errors path="price"/>
			<form:input path="price" class="form-control max-width"/>
		</p>
		<input type="submit" value="Create"/>
	</form:form> 
</body>