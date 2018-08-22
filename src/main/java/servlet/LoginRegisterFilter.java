package servlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Julia on 21.08.2018
 */
public class LoginRegisterFilter extends Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(((HttpServletRequest) request).getSession().getAttribute("access")!=null){
            ((HttpServletResponse) response).sendRedirect("/");
        }else {
            super.doFilter(request, response, chain);
        }
    }
}
