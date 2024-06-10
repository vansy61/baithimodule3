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
    <style>
        body {
            background-color: #f5f5f5;
        }
        .line-bg-gradient{
            background-image: linear-gradient(to right, rgb(255, 62, 52), rgb(255, 203, 66));
            border-radius: 3px;
        }
        #demo {
            margin-left: 10px;
            margin-bottom: 0px;
        }
    </style>

</head>
<body>
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-12 col-lg-6">
            <div class="shadow-sm rounded p-3 bg-white">
                <h4 class="mb-3">Sửa sản phẩm</h4>
                <form method="post" action="${product.id != 0 ? "/product/update" : "/product/create"}">
                    <c:if test="${product.id != 0}">
                        <input type="hidden" name="id" value="${product.id}">
                    </c:if>

                    <div class="mb-3">
                        <label for="name" class="form-label">Nhập tên sản phẩm</label>
                        <input type="text" class="form-control" value="${product.name}" id="name"
                               placeholder="Nhập tên sản phẩm mới" name="name" required>
                        <c:if test="${!product.getErrors().isEmpty()}">
                            <p class="text-danger mt-1"><small>${product.getErrors().get("name")}</small></p>
                        </c:if>
                    </div>

                    <div class="mb-3">
                        <label for="price" class="form-label">Nhập giá</label>
                        <input type="number" class="form-control" value="<fmt:formatNumber value="${product.price}" type="number" pattern="###.###" />" id="price"
                               placeholder="Nhập giá" name="price" required min="10000000">
                        <c:if test="${!product.getErrors().isEmpty()}">
                            <p class="text-danger mt-1"><small>${product.getErrors().get("price")}</small></p>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label for="quantity" class="form-label">Nhập số lượng</label>
                        <input type="number" class="form-control" value="${product.quantity}" id="quantity"
                               placeholder="Nhập số lượng mới" name="quantity" required min="1">
                        <c:if test="${!product.getErrors().isEmpty()}">
                            <p class="text-danger mt-1"><small>${product.getErrors().get("quantity")}</small></p>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Màu sắc</label>
                        <select class="form-select mb-3" name="color">
                            <option value="Đỏ">Đỏ</option>
                            <option value="Xanh">Xanh</option>
                            <option value="Đen">Đen</option>
                            <option value="Trắng">Trắng</option>
                            <option value="Vàng">Vàng</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Nhập mô tả</label>
                        <input type="text" class="form-control" value="${product.description}" id="description"
                               placeholder="Nhập mô tả mới" name="description">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Phân loại</label>
                        <select class="form-select mb-3" name="category_id">
                            <c:forEach var="item" items="${categories}">
                                <option ${product.categoryId == item.id ? "selected" : ""}
                                        value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>


                    <div class="d-grid gap-2 mt-5">
                        <button type="submit" class="btn btn-primary btn-lg">Lưu</button>
                        <a href="/product/list" class="btn btn-outline-secondary">Hủy</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>
