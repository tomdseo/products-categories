<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> <!--important line to display jstl form -->    
<%@ page contentType="text/html; charset=UTF-8" %>  <!--important line to display emojis-->
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href='<c:url value="/css/style.css"/>' type="text/css"/>
	<title></title>
</head>
<body>
	<h1>Category | <c:out value="${category.name}"/></h1>
	<!--TABLE & OUTPUT----------------------------------------- -->
	<table class="table">
		<thead class="thead-dark">
		        <tr>
		            <th>Products</th>
		        </tr>
		</thead>
		<tbody>
			<c:if test="${category.products.size() > 0}">
		        <c:forEach items="${category.products}" var="product">
		        <tr>
		            <td>-<c:out value="${product.name}"/></td>
		        </tr>
		        </c:forEach>
		   	</c:if>
		   	<c:if test="${category.products.size() == 0}">
		    	<tr>
		    		<td>EMPTY: Add Products to Your Category</td>
		    	</tr>
		    </c:if>
		</tbody>
	</table>
	
	<div class="mt-5 mb-5">
	<h3>Add Product</h3>
	<!--  SELECTOR  -->
	<form action="/categories/product/add/${category.id}" method="POST">
		<select name="product_add">
			<c:if test="${nonProducts.size() > 0}">
			    <c:forEach items="${nonProducts}" var="product">
		   			<option value="${product.id}">${product.name}</option>
			    </c:forEach>
			</c:if>
			<c:if test="${nonProducts.size() == 0}">
				<option disabled selected>EMPTY: Pre-populate Products</option>
			</c:if>
    	</select>
		<input type="submit" value="Select"/>
	</form> 
	<a href="/products/new" class="btn btn-success btn-sm">Or Create a New Product</a>
    </div>
</body>
