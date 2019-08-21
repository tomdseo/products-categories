<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> <!--important line to display jstl form -->    
<%@ page contentType="text/html; charset=UTF-8" %>  <!--important line to display emojis-->
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href='<c:url value="/css/style.css"/>' type="text/css"/>
	<title></title>
</head>
<body>
	<h1>Product | <c:out value="${product.name}"></c:out></h1>
	<!--TABLE & OUTPUT----------------------------------------- -->
	<table class="table">
		<thead class="thead-dark">
		        <tr>
		            <th>Categories</th>
		        </tr>
		</thead>
		<tbody>
			<c:if test="${product.categories.size() > 0}">
		        <c:forEach items="${product.categories}" var="category">
		        <tr>
		            <td>-<c:out value="${category.name}"/></td>
		        </tr>
		        </c:forEach>
		    </c:if>
		    <c:if test="${product.categories.size() == 0}">
		    	<tr>
		    		<td>EMPTY: Add Categories to Your Product</td>
		    	</tr>
		    </c:if>
		</tbody>
	</table>
	
	<div class="mt-5 mb-5">
	<h3>Add Category</h3>
	<!--  SELECTOR  -->
	<form action="/products/category/add/${product.id}" method="POST">
		<select name="category_add">
			<c:if test="${nonCategories.size() > 0}">
			    <c:forEach items="${nonCategories}" var="category">
		   			<option value="${category.id}">${category.name}</option>
			    </c:forEach>
			</c:if>
			<c:if test="${nonCategories.size() == 0}">
				<option disabled selected>EMPTY: Pre-populate Categories</option>
			</c:if>
    	</select>
		<input type="submit" value="Select"/>
	</form>
	<a href="/categories/new" class="btn btn-success btn-sm">Or Create a New Category</a>
    </div>
    
    <!--TABLE & OUTPUT----------------------------------------- -->
    <p class="lead">All About Your Product</p>
	<table class="table">
		<thead class="thead-dark">
		        <tr>
		            <th>Description</th>
		            <th>Price</th>
		        </tr>
		</thead>
		<tbody>
	        <tr>
	            <td><c:out value="${product.description}"/></td>
	            <td><c:out value="${product.price}"/></td>
	        </tr>
		</tbody>
	</table>
</body>