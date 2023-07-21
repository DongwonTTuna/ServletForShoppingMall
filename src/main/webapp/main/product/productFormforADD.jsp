<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <meta charset="UTF-8">
</head>
<body
  style="
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  "
>
  <form
    action="/school/product/add"
    method="post"
    style="
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
      height: 500px;
      border: 1px solid black;
      border-radius: 5px;
      padding: 30px;
    "
  >
    <label for="productID">
      <span>Product ID : </span>
      <input type="hidden" name="productID" value="${product.id}" />
      <input type="text" name="productID" value="${product.id}" disabled />
    </label>
    <label for="productName">
      <span>Product Name : </span>
      <input type="text" name="productName" value="${product.productName}" />
    </label>
    <label for="productDescription" style="display: flex; align-items: center;">
      <span>Product Description : </span>
      <textarea name="productDescription" id="" cols="30" rows="10">${product.productDescription}</textarea>
    </label>
    <label for="productPrice">
      <span>Product Price : </span>
      <input
        type="number"
        name="productPrice"
        value="${product.productPrice}"
      />
    </label>
    <label for="productStock">
      <span>Product Stock : </span>
      <input
        type="number"
        name="productStock"
        value="${product.productStock}"
      />
    </label>
    <label for="productStatus">
      <span>Product Status : </span>
      <select name="productStatus" id="">
        <c:if test="${product.productStatus == 0}">
          <option value="0" selected>Available</option>
          <option value="1">Unavailable</option>
        </c:if>
        <c:if test="${product.productStatus == 1}">
          <option value="0">Available</option>
          <option value="1" selected>Unavailable</option>
        </c:if>
      </select>
    </label>
    <input type="submit" value="Submit" />
  </form>
</body>
