<%@ page contentType="text/html; charset=UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
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
      action="/school/auth/register"
      method="post"
      style="
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: center;
        height: 200px;
        border: 1px solid black;
        padding: 30px;
        border-radius: 5px;
      "
    >
      <label for="id">
        Email: <input type="text" name="userEmail" id="id" />
      </label>
      <label for="password">
        Password: <input type="password" name="userPassword" id="password" />
      </label>
      <input type="submit" value="Submit" />
    </form>
  </body>
</html>
