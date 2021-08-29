package servlet.userServlet;
/**
 * @className:${NAME}
 * @description:
 * @author: renhai
 * @create: 2021-08-24 20:43
 */

import domain.user;
import org.apache.commons.beanutils.BeanUtils;
import service.ServiceImpl.userServiceImpl;
import service.userService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/insertServlet")
public class insertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得注册的对象
        Map<String, String[]> map = request.getParameterMap();
        user user1 = new user();
        try {
            BeanUtils.populate(user1, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //检测是否用户存在
        userService userService = new userServiceImpl();
        user user2 = userService.findOneRegister(user1);
        System.out.println(user2);
    }
}
