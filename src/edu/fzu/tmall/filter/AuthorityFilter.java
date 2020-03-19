package edu.fzu.tmall.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//权限验证过滤器
@WebFilter(filterName = "AuthorityFilter",dispatcherTypes = {DispatcherType.REQUEST },urlPatterns = { "/P*" })
public class AuthorityFilter implements Filter {
    private String logPage="/login.jsp";

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String request_uri = req.getRequestURI();//获取http请求的页面
        String contextPath = req.getContextPath();
        String uri = request_uri.substring(contextPath.length());

        System.out.println(req.getSession().getAttribute("name"));

        if (req.getSession().getAttribute("name") == null) {//判断是否登录过
            if (uri.equals(logPage) || request_uri.indexOf("css/") > 0
                    || request_uri.indexOf("img/") > 0
                    || request_uri.indexOf("scripts/") > 0
                    || uri.equals("/LoginServlet")) {
                chain.doFilter(request, response);
                return;
            }
            else
            {
                res.sendRedirect("login.jsp");
            }
        }
        else chain.doFilter(request, response);
        return;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
