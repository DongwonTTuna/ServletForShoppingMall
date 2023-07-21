import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.CustomerDAO;
import model.Customer;


@WebServlet("/user/remove")
public class RemoveUser extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");
        if (!CustomerDAO.delete(customer)){
            request.getRequestDispatcher("/error/error.jsp");
        }
        session.removeAttribute("customerInfo");
        response.sendRedirect("/school/");
    }
}
