package servlet.commentServlet;

import domain.comment;
import service.ServiceImpl.commentServiceImpl;
import service.commentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
/*评论的删除*/
@WebServlet(name = "deleteCommentServlet", value = "/deleteCommentServlet")
public class deleteCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int commentId=Integer.parseInt(request.getParameter("commentId"));
        comment comment1=new comment();
        comment1.setCommentId(commentId);
        commentService service =new commentServiceImpl();
        service.delete(comment1);
    }
}
