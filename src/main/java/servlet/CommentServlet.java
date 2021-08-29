package servlet;
/**
 * @className:CommentServlet
 * @description:评论servlet
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.blog;
import domain.comment;
import domain.user;
import service.ServiceImpl.commentServiceImpl;
import service.commentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/Comment/*")
public class CommentServlet extends BaseServlet {
/**
 * @Author renhai
 * @Description  删除一条评论
 * @Date 19:20 2021/8/18
 * @Param [request, response]
 * @return void
 **/
    public void Delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int commentId=Integer.parseInt(request.getParameter("commentId"));
        comment comment1=new comment();
        comment1.setCommentId(commentId);
        commentService service =new commentServiceImpl();
        service.delete(comment1);
    }

/**
 * @Author renhai
 * @Description  返回所有评论
 * @Date 19:20 2021/8/18
 * @Param [request, response]
 * @return void
 **/
    public void FindAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commentService service =new commentServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(service.findAll());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
    /**
     * @Author renhai
     * @Description  找到某个博客的评论
     * @Date 19:20 2021/8/18
     * @Param [request, response]
     * @return void
     **/
    public void FindOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId=Integer.parseInt(request.getParameter("blogId"));
        blog blog1=new blog();
        blog1.setBlogsId(blogId);
        commentService service=new commentServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(service.findOne(blog1));
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
    /**
     * @Author renhai
     * @Description  插入一条评论
     * @Date 19:20 2021/8/18
     * @Param [request, response]
     * @return void
     **/
    public void Insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        comment comment1=new comment();
        comment1.setContent(request.getParameter("Content"));
        comment1.setBlogId(Integer.parseInt(request.getParameter("blogId")));
        //从session中得到用户ID
        user user =(user) request.getSession().getAttribute("user");
        int Id=user.getId();
        comment1.setUserId(Id);
        commentService service = new commentServiceImpl();
        service.insert(comment1);
    }
}
