import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Cart;
import model.Customer;
import model.Product;
import DAO.CartDAO;
import DAO.ProductDAO;

@WebServlet("/cart/add")
public class AddCart extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");
        int productId = Integer.parseInt(request.getParameter("productID"));
        long quantity = Long.parseLong(request.getParameter("quantity"));

        Product product = ProductDAO.getProductByID(productId);
        
        // check if the product is in the database
        if (product == null) {
            response.sendRedirect("/school/");
            return;
        }
        // check the product status is active
        if (product.getProductStatus() == 1) {
            response.sendRedirect("/school/");
            return;
        }
        // check if the product stock is enough, if not set the quantity to the stock
        if (product.getProductStock() < quantity) {
            quantity = product.getProductStock();
        }
        // check if the product is already in the cart
        Cart cartInDB = CartDAO.getCartByProductID(product.getId(), customer);
        // if the product is already in the cart, update the quantity
        if (cartInDB != null) {
            cartInDB.setQuantity(cartInDB.getQuantity() + quantity);
            if (CartDAO.update(cartInDB)) {
                response.sendRedirect("/school/");
            } else {
                request.getRequestDispatcher("/error/error.jsp");
            }
        } else {
            // if the product is not in the cart, insert the product to the cart
            Cart cart = new Cart(0, customer.getId(), product.getId(), quantity);
            if (CartDAO.insert(cart)) {
                response.sendRedirect("/school/");
            } else {
                request.getRequestDispatcher("/error/error.jsp");
            }
        }
    }
}
