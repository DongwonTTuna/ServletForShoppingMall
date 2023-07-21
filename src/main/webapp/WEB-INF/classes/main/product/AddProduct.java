import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Customer;
import model.Product;
import DAO.ProductDAO;


@WebServlet("/product/add")
public class AddProduct extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");
        Product product = new Product(0, "", 100, "", 100, 0, customer.getId());
        request.setAttribute("product", product);
        request.getRequestDispatcher("/main/product/productFormforADD.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");

        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        long productPrice = Long.parseLong(request.getParameter("productPrice"));
        long productStock = Long.parseLong(request.getParameter("productStock"));
        long productStatus = Long.parseLong(request.getParameter("productStatus"));
        
        Product product = new Product(
            0,
            productName,
            productPrice,
            productDescription,
            productStock,
            productStatus,
            customer.getId()
        );
        // check the productStatus is 0 or 1
        if (productStatus != 0 && productStatus != 1) {
            request.getRequestDispatcher("/error/error.jsp");
            return;
        }
        product.setProductStatus(productStatus);

        if (ProductDAO.insert(product)) {
            response.sendRedirect("/school/");
        } else {
            request.getRequestDispatcher("/error/error.jsp");
        }
    }
}
