import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Customer;
import model.Product;
import DAO.ProductDAO;


@WebServlet("/")
public class Main extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");
        List<Product> productList = ProductDAO.getAllProducts();
        
        request.setAttribute("customer", customer);
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/main.jsp").forward(request, response);
    }
}
