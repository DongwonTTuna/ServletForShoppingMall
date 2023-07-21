<%@ page contentType="text/html; charset=UTF-8" %>
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
    action="/school/user/edit"
    method="post"
    style="
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
      height: 300px;
      border: 1px solid black;
      padding: 30px;
      border-radius: 5px;
      padding: 30px;
    "
  >
    <label for="userEmail">
      <span>メールアドレス</span>
      <input
        type="email"
        name="userEmail"
        id="userEmail"
        value="${customer.userEmail}"
        required
      />
    </label>
    <label for="userPassword">
      <span>パスワード</span>
      <input
        type="password"
        name="userPassword"
        id="userPassword"
        value="${customer.userPassword}"
        required
      />
    </label>
    <input type="submit" value="Submit" />
  </form>
</body>
