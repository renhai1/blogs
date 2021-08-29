package servlet.blogServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.ServiceImpl.blogServiceImpl;
import service.blogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
/*查询所有博客*/
@WebServlet(name = "findBlogAllServlet", value = "/findBlogAllServlet")
public class findBlogAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        blogService service =new blogServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(service.findAll());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
