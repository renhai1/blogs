package servlet.userServlet;

import functionClass.codeMake;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/getVerifiCode")
public class getVerifiCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用我们的验证码类，生成验证码类对象
        codeMake ivc = new codeMake();
        //获取验证码
        BufferedImage image = ivc.getImage();
        //将验证码的文本存在session中
        request.getSession().setAttribute("codeText", ivc.getText());
        //将验证码图片响应给客户端
        codeMake.output(image, response.getOutputStream());
    }
}
