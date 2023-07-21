<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/main/util/nav.jsp" />
<div style="display: flex; align-items: center; justify-content: center; height:80vh;">
    <div style="display: flex; flex-direction: column; gap: 20px; align-items: center; justify-content: space-between; height: 200px; width: 300px; border: 1px solid black; border-radius: 5px; padding: 30px;">
        <label for="userID">
            User ID : <input type="number" name="" id="userID" value="${customer.id}" disabled>
        </label>
        <label for="userEmail">
            User Email : <input type="text" name="" id="userEmail" value="${customer.userEmail}" disabled>
        </label>
        <div style="width: 150px; display: flex; justify-content: space-between;">
            <a href="/school/user/edit"><button>Edit</button></a>
            <a href="/school/"><button>戻る</button></a>
            <a href="/school/user/remove"><button>退会</button></a>
        </div>
    </div>
</div>