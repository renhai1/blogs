package servlet.commentServlet;

import domain.comment;
import domain.user;
import service.ServiceImpl.commentServiceImpl;
import service.commentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
/*新增评论*/
@WebServlet(name = "insertDeleteServlet", value = "/insertCommentServlet")
public class insertCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
