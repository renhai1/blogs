package servlet.commentServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.blog;
import service.ServiceImpl.commentServiceImpl;
import service.commentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
/*列举一个博客的评论*/
@WebServlet(name = "findCommentOneServlet", value = "/findCommentOneServlet")
public class findCommentOneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                int blogId=Integer.parseInt(request.getParameter("blogId"));
                blog blog1=new blog();
                blog1.setBlogsId(blogId);
                commentService service=new commentServiceImpl();
            ObjectMapper mapper=new ObjectMapper();
            String json=  mapper.writeValueAsString(service.findOne(blog1));
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
    }
}
