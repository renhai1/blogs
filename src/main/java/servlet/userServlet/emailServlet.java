package servlet.userServlet;
/**
 * @className:emailServlet
 * @description:发送邮箱验证码
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import functionClass.sendEmail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "emailServlet", value = "/emailServlet")
public class emailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        sendEmail send=new sendEmail();
        try {
          request.getSession().setAttribute("checkCode",send.send(email));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
