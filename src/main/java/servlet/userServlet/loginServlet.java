package servlet.userServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.user;
import functionClass.ResultInfo;
import service.ServiceImpl.userServiceImpl;
import service.userService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取真正的验证码
        String session_code=(String) request.getSession().getAttribute("codeText");
        //防止验证码复用
        request.removeAttribute("codeText");
        /*先验证验证码，再验证用户是否存在*/
        //获得用户输入的验证码
        String userCode=request.getParameter("captcha");
        //返回前端的对象

        ResultInfo info=new ResultInfo();
        if(!(userCode.equalsIgnoreCase(userCode)))
        {
            info.setFlag(false);
            info.setMsg("code wrong");
        }else
        {
            int Id = Integer.parseInt(request.getParameter("userId"));
            String pas=request.getParameter("password");
            //生成登录对象
            user user1=new user();
            user1.setId(Id);
            user1.setPas(pas);
            //验证登录对象
            userService userService=new userServiceImpl();
            user user2=  userService.findOne(user1);
            if(user2!=null)
            {
                //账号密码正确
                info.setFlag(true);
                info.setMsg("login success");
                //将用户存在session中 用于确定个人博客
                request.getSession().setAttribute("user",user2);
            }else
            {
                //账号或密码错误
                info.setFlag(false);
                info.setMsg("account wrong");
            }
        }
        //返回信息给前端
        ObjectMapper mapper=new ObjectMapper();
        String json= mapper.writeValueAsString(info);
        System.out.println(json);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
