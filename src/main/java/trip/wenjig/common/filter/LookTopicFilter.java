package trip.wenjig.common.filter;

import org.springframework.core.annotation.Order;
import trip.wenjig.util.IsInvalidCheck;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "lookTopicFilter", urlPatterns = "/p/*")
public class LookTopicFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String reqUri = req.getRequestURI();
        if (!IsInvalidCheck.isNumber(reqUri.substring(3))) {
            resp.sendRedirect("/index");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
