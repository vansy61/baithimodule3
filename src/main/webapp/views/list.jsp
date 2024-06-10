<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 6/4/2024
  Time: 1:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Student Manager</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body class="bg-body-tertiary">
<div class="container mt-5 ">
    <c:if test="${fromAction != null}">
        <div class="alert alert-success" role="alert">
            <c:if test="${fromAction == 'create'}">
                Tạo sản phẩm thành công!
            </c:if>
            <c:if test="${fromAction == 'update'}">
                Sửa sản phẩm thành công!
            </c:if>
            <c:if test="${fromAction == 'delete'}">
                Xóa sản phẩm thành công!
            </c:if>
        </div>
    </c:if>

    <div>
        <div class="d-flex justify-content-between p-3 shadow-sm bg-white mb-3 align-items-center">
            <h6 class="text-decoration-none text-secondary mb-0 ">Tất cả sản phẩm</h6>
            <div>
                <a class="btn btn-sm btn-primary px-4" type="submit" href="/product/create">Thêm sản phẩm mới</a>
            </div>
        </div>
        <div class="p-3 bg-white mb-2 shadow-sm">
            <div class="mb-3">

                        <form action="/product/list">
                            <div class="row">
                                <div class="col">
                                    <input type="text" class="form-control" placeholder="Tên sản phẩm" name="name" value="${name}">
                                </div>
                                <div class="col">
                                    <input type="number" class="form-control" placeholder="Giá sản phẩm" name="price" value="<fmt:formatNumber value="${price}" type="number" pattern="###.###" />">
                                </div>
                                <div class="col">
                                    <button class="btn btn-outline-primary ms-3">Tìm kiếm</button>
                                </div>
                        </div>
                    </form>

            </div>
            <div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Tên sản phẩm</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Màu sắc</th>
                        <th>Mô tả</th>
                        <th>Danh mục</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${products}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${product.name}</td>
                            <td><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="đ" /></td>
                            <td>${product.quantity}</td>
                            <td>${product.color}</td>
                            <td>${product.description}</td>
                            <td>${product.category.name}</td>
                            <td class="text-nowrap">
                                <a href="/product/delete?id=${product.id}" class="btn-destroy btn btn-sm btn-outline-danger">
                                    <i class="fa-solid fa-trash"></i>
                                    Xoá
                                </a>
                                <a href="/product/update?id=${product.id}"
                                   class="btn btn-sm btn-outline-primary ms-1">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                    Sửa
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/custom.js"></script>


</body>
</html>
