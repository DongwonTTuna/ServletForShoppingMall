<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(240px, 1fr)); place-items: center; align-items: stretch;">
    <c:forEach var="product" items="${productList}">
        <c:if test="${product.productStock > 0 && (product.ownerID == customer.id || product.productStatus == 0)}" >
            <div style="min-height: 200px; width: 200px; margin: 25px 0; border: 1px solid black; border-radius: 5px; padding: 20px; display: flex; flex-direction: column; justify-content: space-around; align-items: center;">
                <h3>${product.productName}</h3>
                <p>${product.productDescription}</p>
                <div style="display:flex; justify-content: space-around; align-items: center;">
                    <p style="padding-right: 20px;">Price : ${product.productPrice}</p>
                    <p>stock : ${product.productStock}</p>
                </div>
                <div style="display:flex; justify-content: space-around; align-items: center;">
                    <c:if test="${product.productStatus == 0}">
                        <a style="padding-right: 20px;" href="/school/cart/add?productID=${product.id}&quantity=1">Add to cart</a>
                    </c:if>
                    <c:if test="${product.ownerID == customer.id}">
                        <a href="/school/product/edit?productID=${product.id}">Edit</a>
                    </c:if>
                </div>
            </div>
        </c:if>
    </c:forEach>
    <div style="min-height: 200px; min-width: 200px; margin: 25px 0; border: 1px solid black; border-radius: 5px; padding: 20px; display: flex; flex-direction: column; justify-content: space-around; align-items: center;">
        <a href="/school/product/add"><button>Add Product</button></a>
    </div>
</div>
