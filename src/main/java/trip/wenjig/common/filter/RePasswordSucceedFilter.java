package trip.wenjig.common.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "rePasswordSetFilter", urlPatterns = "/a/rePassword.succeed")
public class RePasswordSucceedFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getSession().getAttribute("vCode") != null && (long)req.getSession().getAttribute("updateUserId") != 0L) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            resp.sendRedirect("/index");
        }
    }

    @Override
    public void destroy() {

    }
}
