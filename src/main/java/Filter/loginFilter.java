//package Filter;
///**
// * @className:loginFilter
// * @description:是否登录验证
// * @author: renhai
// * @create: 2021-08-17 16:58
// */
//import com.fasterxml.jackson.databind.ObjectMapper;
//import functionClass.ResultInfo;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@WebFilter("/*")
//public class loginFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//       // Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
//        System.out.println(req);
//        HttpServletRequest request = (HttpServletRequest) req;
//
//        String uri = request.getRequestURI();

//        if(uri.contains("login.html") || uri.contains("/user/Login") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/") || uri.contains("/user/Register")||uri.contains("/user/GetCode")||uri.contains("index.html")||uri.contains("")){
//
//            chain.doFilter(req, resp);
//        }else{
//            Object user = request.getSession().getAttribute("user");
//            if(user != null){
//
//                chain.doFilter(req, resp);
//            }else{
//                ResultInfo info=new ResultInfo();
//                info.setFlag(false);
//                info.setMsg("未登录，请重新登录");
//                ObjectMapper mapper=new ObjectMapper();
//                String json= mapper.writeValueAsString(info);
//                resp.setContentType("application/json;charset=utf-8");
//                resp.getWriter().write(json);
//                request.getRequestDispatcher("/login.html").forward(request,resp);
//            }
//        }
//    }
//
//    @Override
//    public void destroy() {
//       // Filter.super.destroy();
//    }
//}
