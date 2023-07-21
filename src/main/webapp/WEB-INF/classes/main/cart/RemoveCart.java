import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.CartDAO;
import model.Cart;
import model.Customer;

@WebServlet("/cart/remove")
public class RemoveCart extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");
        long cartID = Long.parseLong(request.getParameter("cartID"));
        Cart cart = CartDAO.getCartByID(cartID, customer);
        if (cart == null) {
            request.getRequestDispatcher("/error/error.jsp");
            return;
        }
        if(CartDAO.delete(cart)){
            response.sendRedirect("/school/cart");
        } else {
            request.getRequestDispatcher("/error/error.jsp");
        }
    }
}
