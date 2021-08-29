package servlet.commentServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.ServiceImpl.commentServiceImpl;
import service.commentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
/*列举所有评论*/
@WebServlet(name = "findCommentAllServlet", value = "/findCommentAllServlet")
public class findCommentAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commentService service =new commentServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(service.findAll());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
