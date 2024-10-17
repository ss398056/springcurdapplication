<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page isELIgnored="false" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details Table</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .table-container {
            max-width: 1000px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        .table-title {
            font-size: 24px;
            margin-bottom: 20px;
            font-weight: bold;
            text-align: center;
            color: #343a40;
        }
        .table-striped tbody tr:nth-of-type(odd) {
            background-color: #f2f2f2;
        }
        .action-btn {
            margin-right: 10px;
        }
        .btn-edit {
            background-color: #28a745;
            color: #fff;
            transition: background-color 0.3s;
        }
        .btn-edit:hover {
            background-color: #218838;
        }
        .btn-delete {
            background-color: #dc3545;
            color: #fff;
            transition: background-color 0.3s;
        }
        .btn-delete:hover {
            background-color: #c82333;
        }
        .setimg{
        	height : 50px;
        	wigth : 50px;
        }
    </style>
</head>
<body>

<div class="container table-container">
    
 	<a href="<%= request.getContextPath() %>/addproduct" ><button class="btn btn-primary">Add Product</button></a>
    <h2 class="table-title">Product Details</h2>
    <table class="table table-hover table-striped">
        <thead class="table-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Product Name</th>
                <th scope="col">Description</th>
                <th scope="col">Price</th>
                <th scope="col">Image</th>
                <th scope="col">Actions</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${products }" var="p">
            <tr>
                <th scope="row">${p.id}</th>
                <td>${p.name }</td>
                <td>${p.description }</td>
                <td>${p.price }</td>
                <td><img class="setimg" src="<c:url value='/resources/images/${p.image }' />" alt="pic"></td>
                <td>
                    <a href="<%= request.getContextPath() %>/${p.id}"><button class="btn btn-sm btn-edit action-btn">Edit</button></a>
                    <a href="<%= request.getContextPath() %>/deleteproduct/${p.id}" ><button class="btn btn-sm btn-delete">Delete</button></a>
                </td>
            </tr>
            </c:forEach>
            
        </tbody>
    </table>
    
</div>

<!-- Bootstrap 5 JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
