import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Customer;
import model.Order;
import DAO.ProductDAO;
import model.Cart;
import DAO.CartDAO;
import DAO.OrderDAO;

import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/order/submit")
public class AddOrder extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");

        List<Cart> carts = CartDAO.getCartsWithProductInfoByUser(customer);

        if (carts == null) {
            System.out.println("carts is null");
            request.getRequestDispatcher("/error/error.jsp").forward(request, response);
            return;
        }

        List<Cart> filteredCarts = carts.stream().filter(cart -> cart.getProductStatus() == 0).collect(Collectors.toList());
        // if all the products are inactivated, then delete all the carts and redirect to the index page
        if (filteredCarts.isEmpty()) {
            carts.forEach(cart -> CartDAO.delete(cart));
            request.getRequestDispatcher("/school/").forward(request, response);
            return;
        }

        // if the product stocks are not enough, then set the quantity to the product stock.
        filteredCarts.forEach(cart -> {
            if (cart.getProductStock() < cart.getQuantity()) {
                cart.setQuantity(cart.getProductStock());
            }
        });
        // make carts to orders
        List<Order> orders = filteredCarts.stream().map(cart -> cart.toOrder()).collect(Collectors.toList());

        // if the orders are empty, then redirect to the error page
        if (orders.isEmpty()) {
            System.out.println("orders is empty");
            request.getRequestDispatcher("/error/error.jsp").forward(request, response);
            return;
        }
        // insert the orders to the database
        orders.stream().forEach(order -> OrderDAO.insert(order));

        // Update the product stock
       filteredCarts.forEach(cart -> {
            long productId = cart.getProductID();
            long quantity = cart.getQuantity();
            ProductDAO.updateOnlyQuantity(productId, quantity);
        });

        // delete the carts
        carts.forEach(cart -> CartDAO.delete(cart));


        response.sendRedirect("/school/");
    }
}
