package servlet.userServlet;
/**
 * @className:code_authentication
 * @description:注册功能
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.user;
import functionClass.ResultInfo;
import service.ServiceImpl.userServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import service.userService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
/**
 * 用户注册*/
@WebServlet("/code_authentication")
public class code_authentication extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //从session中获取真正的验证码
        String session_code=(String) request.getSession().getAttribute("codeText");
        //防止验证码复用
        request.removeAttribute("codeText");
        /*
           先验证验证码，再验证用户是否存在
         */
        //获得用户输入的验证码
        String userCode=request.getParameter("captcha");
        //返回前端的对象
        ResultInfo info=new ResultInfo();

        if(!(userCode.equalsIgnoreCase(session_code)))
        {
            //验证码错误
            info.setFlag(false);
            info.setMsg("code wrong");
        }else
        {
            //验证码正确
//            String Name=request.getParameter("userName");
//            String pas=request.getParameter("password");
//            String emailAddress=request.getParameter("email");
//            user user1=new user();
//            user1.setName(Name);
//            user1.setPas(pas);
//            user1.setEmail(emailAddress);

            //得到真正的邮箱验证码
            String status=(String) request.getSession().getAttribute("checkCode");
            //清除旧的邮箱验证码
            request.removeAttribute("checkCode");
            //获得注册的对象
            Map<String,String[]>map=request.getParameterMap();
            user user1=new user();
            try {
                BeanUtils.populate(user1,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //检测是否用户存在
            userService userService=new userServiceImpl();
          user user2=  userService.findOneRegister(user1);
            if(user2!=null)
            {
                //非空，用户存在
                info.setFlag(false);
                info.setMsg("用户已存在");
            }else
            {
                //用户不存在
                if(user1.getCode()!=status)
                {
                    info.setFlag(false);
                    info.setMsg("邮箱验证码错误");
                }else
                {
                    userService.insert(user1);
                    user user3=  userService.findOneRegister(user1);
                    info.setFlag(true);
                    info.setMsg("注册成功，账号为："+user3.getId());
                }
            }
        }

        //返回信息给前端
        ObjectMapper mapper=new ObjectMapper();
        String json= mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
