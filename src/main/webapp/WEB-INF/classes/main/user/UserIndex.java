import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Customer;


@WebServlet("/user")
public class UserIndex extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/main/user/main.jsp").forward(request, response);
    }
}
