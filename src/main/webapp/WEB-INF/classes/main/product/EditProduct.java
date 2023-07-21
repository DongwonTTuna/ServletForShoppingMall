import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Customer;
import model.Product;
import DAO.ProductDAO;


@WebServlet("/product/edit")
public class EditProduct extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long productID = Long.parseLong(request.getParameter("productID"));
        Product product = ProductDAO.getProductByID(productID);
        if (product == null) {
            request.getRequestDispatcher("/error/error.jsp");
            return;
        }
        request.setAttribute("product", product);
        request.getRequestDispatcher("/main/product/productForm.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");

        long productID = Long.parseLong(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        long productPrice = Long.parseLong(request.getParameter("productPrice"));
        long productStock = Long.parseLong(request.getParameter("productStock"));
        long productStatus = Long.parseLong(request.getParameter("productStatus"));

        Product product = ProductDAO.getProductByID(productID);
        if (product == null) {
            request.getRequestDispatcher("/error/error.jsp");
            return;
        }
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductPrice(productPrice);
        product.setProductStock(productStock);
        // check the productStatus is 0 or 1
        if (productStatus != 0 && productStatus != 1) {
            request.getRequestDispatcher("/error/error.jsp");
            return;
        }
        product.setProductStatus(productStatus);

        if (ProductDAO.update(product, customer)) {
            response.sendRedirect("/school/");
        } else {
            request.getRequestDispatcher("/error/error.jsp");
        }
    }
}
