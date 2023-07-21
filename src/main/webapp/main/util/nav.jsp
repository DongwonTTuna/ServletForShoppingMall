<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="display: flex; align-items: center; justify-content: space-around;">
    <div><a href="/school/">Products</a></div>
    <div><a href="/school/cart">Cart</a></div>
    <div><a href="/school/orders">Orders</a></div>
    <div><a href="/school/user">User</a></div>
    <jsp:include page="/main/util/userNav.jsp" />
</div>