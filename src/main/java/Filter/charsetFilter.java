package Filter;
/**
 * @Author renhai
 * @Description  字符设置（utf-8）
 * @Date 17:43 2021/8/17
 **/
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/*")
public class charsetFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //交给下一个过滤器或servlet处理
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {

    }
}
