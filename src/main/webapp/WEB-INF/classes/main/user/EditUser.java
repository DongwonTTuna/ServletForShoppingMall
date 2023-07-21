import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Customer;
import DAO.CustomerDAO;


@WebServlet("/user/edit")
public class EditUser extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/main/user/userForm.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");
        customer.setUserEmail(userEmail);
        customer.setUserPassword(userPassword);
        if (CustomerDAO.update(customer)) {
            session.setAttribute("customerInfo", customer);
            response.sendRedirect("/school/");
        } else {
            request.getRequestDispatcher("/error/error.jsp");
        }
    }
}
