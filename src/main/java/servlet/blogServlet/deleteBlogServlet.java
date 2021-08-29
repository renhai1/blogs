package servlet.blogServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.blog;
import domain.user;
import functionClass.ResultInfo;
import service.ServiceImpl.blogServiceImpl;
import service.blogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
/*博客删除功能*/
@WebServlet(name = "deleteBlogServlet", value = "/deleteBlogServlet")
public class deleteBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session获得用户id
        user user =(user) request.getSession().getAttribute("user");
        int userId=user.getId();
        int blogId= Integer.parseInt(request.getParameter("blogId"));
        blog blog1=new blog();
        blog1.setBlogsId(blogId);
        blog1.setUserId(userId);
        blogService service =new blogServiceImpl();
        service.delete(blog1);
        //返回信息
        ResultInfo info=new ResultInfo();
        info.setFlag(true);
        info.setMsg("删除成功");
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }
}
