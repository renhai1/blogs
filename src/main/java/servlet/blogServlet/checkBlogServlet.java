package servlet.blogServlet;

import domain.blog;
import service.ServiceImpl.blogServiceImpl;
import service.ServiceImpl.commentServiceImpl;
import service.blogService;
import service.commentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "checkBlogServlet", value = "/checkBlogServlet")
public class checkBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int blogId=Integer.parseInt(request.getParameter("blogId"));
            blog blog1=new blog();
            blog1.setBlogsId(blogId);
            blogService service =new blogServiceImpl();
            service.check(blog1);
    }
}
