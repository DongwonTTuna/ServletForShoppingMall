import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Customer;
import DAO.CustomerDAO;


@WebServlet("/auth/login")
public class Login extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
    }

    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

        Customer customer = CustomerDAO.login(userEmail, userPassword);
        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("customerInfo", customer);
            request.getRequestDispatcher("/auth/loginSuccess.jsp").forward(request, response);
            return;
        }else {
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        }
    }
}
