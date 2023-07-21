import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import model.Customer;

@WebFilter(urlPatterns = { "/*" })
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        if (!requestURI.contains("/auth/")) {
            HttpSession session = httpRequest.getSession();
            Customer customer = (Customer) session.getAttribute("customerInfo");

            if (customer == null) {
                RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
                rd.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
