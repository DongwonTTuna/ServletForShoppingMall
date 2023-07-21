<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/main/util/nav.jsp" />
<div style="font-size: 32px; text-align: center; margin-top:40px;"><h1>ORDERS</h1></div>
<div style="display: flex; flex-direction: column; justify-content: space-around;  align-items: center; gap: 50px;">
    <c:forEach var="order" items="${orderList}">
        <div style="position:relative; border: 1px solid black; border-radius: 5px; padding: 20px; width: 600px;">
            <div style="position: absolute; right: 0px; top: 0px; display: flex; gap: 5px;">
                <div style="border:1px solid black; padding: 5px; border-radius: 5px 0;"><a href="/school/order/remove?orderID=${order.id}">X</a></div>
            </div>
            <div>
                <label for="orderID">
                    Order ID : <input type="number" name="orderID" id="orderID" value="${order.id}" disabled>
                </label>
                <label for="productName" style="margin-left: 40px;">
                    Product Name : <input type="text" name="productName" id="productName" value="${order.productName}" disabled>
                </label>
            </div>
            <div style="margin-top: 20px;">
                <label for="quantity">
                    Quantity : <input type="number" name="quantity" id="quantity" value="${order.quantity}" disabled>
                </label>
            </div>
        </div>
    </c:forEach>
    <a href="/school/"><button>戻る</button></a>
</div>
