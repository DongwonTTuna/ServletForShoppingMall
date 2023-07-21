import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Customer;
import DAO.CartDAO;
import model.Cart;
import java.util.List;


@WebServlet("/cart")
public class CartIndex extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");
        List<Cart> carts = CartDAO.getCartsWithProductInfoByUser(customer);
        if(carts == null){
            response.sendRedirect("/school/");
            return;
        }
        long totalPrice = 0;
        for (Cart cart : carts) {
            totalPrice += cart.getQuantity() * cart.getProductPrice();
        }
        request.setAttribute("customer", customer);
        request.setAttribute("cartList", carts);
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("/main/cart/main.jsp").forward(request, response);
    }
}
