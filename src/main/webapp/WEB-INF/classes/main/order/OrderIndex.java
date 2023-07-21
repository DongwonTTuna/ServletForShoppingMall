import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Customer;
import model.Order;
import DAO.OrderDAO;
import java.util.List;

@WebServlet("/orders")
public class OrderIndex extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");
        List<Order> orders = OrderDAO.getAllOrderListsWithProductName(customer);
        if(orders == null){
            response.sendRedirect("/school/");
            return;
        }
        request.setAttribute("customer", customer);
        request.setAttribute("orderList", orders);
        request.getRequestDispatcher("/main/orders/main.jsp").forward(request, response);
    }
}
