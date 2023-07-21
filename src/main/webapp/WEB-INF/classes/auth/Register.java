import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.CustomerDAO;
import model.Customer;

@WebServlet("/auth/register")
public class Register extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

        if (userEmail == null || userPassword == null) {
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
            return;
        }

        Customer customer = new Customer(0, userEmail, userPassword);
        CustomerDAO.insert(customer);
        request.getRequestDispatcher("/auth/registerSuccess.jsp").forward(request, response);
    }
}
