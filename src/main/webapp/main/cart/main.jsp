<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/main/util/nav.jsp" />
<div style="font-size: 32px; text-align: center; margin-top:40px;"><h1>IN YOUR CART</h1></div>
<div style="display: flex; flex-direction: column; justify-content: space-around;  align-items: center; gap: 50px;">
    <c:forEach var="cart" items="${cartList}">
        <div style="position:relative; border: 1px solid black; border-radius: 5px; padding: 20px; width: 600px;">
            <div style="position: absolute; right: 0px; top: 0px; display: flex; gap: 5px;">
                <div style="border:1px solid black; padding: 5px; border-radius: 5px 0;"><a href="/school/cart/edit?cartID=${cart.id}">E</a></div>
                <div style="border:1px solid black; padding: 5px; border-radius: 5px 0;"><a href="/school/cart/remove?cartID=${cart.id}">X</a></div>
            </div>
            <div>
                <label for="ID">
                    Cart ID : <input type="number" name="" id="ID" value="${cart.id}" disabled>
                </label>
                <label for="productName" style="margin-left: 40px;">
                    ProductName : <input type="text" name="" id="productName" value="${cart.productName}" disabled>
                </label>
            </div>
            <div style="margin-top: 20px;">
                <label for="productQuantity">
                    Quantity : <input type="number" name="" id="productQuantity" value="${cart.quantity}" disabled>
                </label>
                <label for="productPrice" style="margin-left: 40px;">
                    Total Price : <input type="number" name="" id="productPrice" value="${cart.productPrice * cart.quantity}" disabled>
                </label>
            </div>
        </div>
    </c:forEach>
    <div style="margin-top: 100px;">Total Price In Cart : ${totalPrice}円</div>
    <div>
        <a href="/school/"><button>戻る</button></a>
        <a href="/school/order/submit"><button>注文する</button></a>
    </div>

</div>