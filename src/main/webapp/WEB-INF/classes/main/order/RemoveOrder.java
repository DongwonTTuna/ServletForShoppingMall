import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Customer;
import model.Order;
import DAO.OrderDAO;


@WebServlet("/order/remove")
public class RemoveOrder extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerInfo");

        long orderID = Long.parseLong(request.getParameter("orderID"));
        Order order = OrderDAO.getOrderByID(orderID, customer);

        if (order == null) {
            System.out.println("order is null");
            request.getRequestDispatcher("/error/error.jsp").forward(request, response);
            return;
        }

        OrderDAO.delete(order);

        response.sendRedirect("/school/orders");
    }
}
