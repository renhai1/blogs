package servlet.blogServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.user;
import service.ServiceImpl.blogServiceImpl;
import service.blogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
//某个用户的博客
@WebServlet(name = "findBlogOneServlet", value = "/findBlogOneServlet")
public class findBlogOneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中得到用户ID
        user user =(user) request.getSession().getAttribute("user");
        int userId=user.getId();
        user user1=new user();
        user1.setId(userId);
        blogService service =new blogServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(service.findOne(user1));
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
