import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Cart;
import model.Customer;
import model.Product;
import DAO.CartDAO;
import DAO.ProductDAO;


@WebServlet("/cart/edit")
public class EditCart extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");
        long cartId = Long.parseLong(request.getParameter("cartID"));
        Cart cart = CartDAO.getCartWithProductInfoByUserAndCartID(cartId, customer);
        if (cart == null) {
            request.getRequestDispatcher("/error/error.jsp");
            return;
        }
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/main/cart/cartForm.jsp").forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");
        //TODO: form validation
        long cartId = Long.parseLong(request.getParameter("cartID"));
        long quantity = Long.parseLong(request.getParameter("quantity"));
        
        // check if the cart is in the database
        Cart cart = CartDAO.getCartByID(cartId, customer);
        if (cart == null) {
            request.getRequestDispatcher("/error/error.jsp");
            return;
        }
        Product product = ProductDAO.getProductByID(cart.getProductID());
        // check if the product is in the database
        if (product == null) {
            CartDAO.delete(cart);
            request.getRequestDispatcher("/error/error.jsp");
            return;
        }
        // check if the product status is active
        if (product.getProductStatus() == 1) {
            CartDAO.delete(cart);
            request.getRequestDispatcher("/error/error.jsp");   
        }

        //check if the quantity is more than the stock
        if (product.getProductStock() < quantity) {
            quantity = product.getProductStock();
        }

        // update the quantity
        cart.setQuantity(quantity);
        
        // update the cart
        if (CartDAO.update(cart)){
            response.sendRedirect("/school/cart");
        } else {
            request.getRequestDispatcher("/error/error.jsp");
        }
    }
}
