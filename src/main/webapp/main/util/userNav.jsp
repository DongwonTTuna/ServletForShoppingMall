<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="border: 1px solid black; border-radius: 5px; padding: 0 20px; text-align: center; display: flex; flex-direction: column; align-items: center; justify-content: center;">
    <p>hello ${customer.userEmail}</p>
    <a href="/school/auth/logout">Logout</a>
</div>