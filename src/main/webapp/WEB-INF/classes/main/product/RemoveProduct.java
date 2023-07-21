import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Customer;
import model.Product;
import DAO.ProductDAO;


@WebServlet("/product/remove")
public class RemoveProduct extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");
        long productID = Long.parseLong(request.getParameter("productID"));
        Product product = ProductDAO.getProductByID(productID);
        if (product == null) {
            request.getRequestDispatcher("/error/error.jsp").forward(request, response);
            return;
        }
        ProductDAO.delete(product, customer);
        response.sendRedirect("/school/");
    }
}
