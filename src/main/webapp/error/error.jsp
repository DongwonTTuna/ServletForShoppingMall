<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<body style="display: flex; align-items: center; justify-content: center;">
    <div style="height: 800px; width: 600px; display: flex; flex-direction: column; justify-content: space-between; align-items: center;">
        <h1>!!! Error !!!</h1>
        <img src="https://w7.pngwing.com/pngs/595/505/png-transparent-computer-icons-error-closeup-miscellaneous-text-logo.png" alt="errorImg" style="height: 200px;">
        <p>処理を正常に行えませんでした。</p>
    </div>
    <script>
        setTimeout(function() {
            location.href = "/school/";
        }, 2500);
    </script>
</body>