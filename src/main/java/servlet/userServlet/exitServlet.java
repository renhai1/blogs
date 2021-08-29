package servlet.userServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
/*用户退出*/
@WebServlet(name = "exitServlet", value = "/exitServlet")
public class exitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           //销毁session
            request.getSession().invalidate();
           // response.sendRedirect(request.getContextPath()+"登录页面");
    }
}
