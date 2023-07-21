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
    action="/school/cart/edit"
    method="post"
    style="
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
      height: 300px;
      border: 1px solid black;
      border-radius: 5px;
      padding: 30px;
    "
  >
    <label for="cartId">
      <span>Cart ID</span>
      <input type="hidden" name="cartID" value="${cart.id}" />
      <input type="text" name="cartID" value="${cart.id}" disabled />
    </label>
    <label for="productId">
      <span>Product ID</span>
      <input type="hidden" name="productID" value="${cart.productID}" />
      <input type="text" name="productID" value="${cart.productName}" disabled />
    </label>
    <label for="quantity">
      <span>Quantity</span>
      <input type="number" name="quantity" value="${cart.quantity}" />
    </label>
    <input type="submit" value="Submit" />
  </form>
</body>
