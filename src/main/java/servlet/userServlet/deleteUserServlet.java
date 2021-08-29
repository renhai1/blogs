package servlet.userServlet;
/**
 * @className:deleteUserServlet
 * @description:删除用户
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.user;
import functionClass.ResultInfo;
import service.ServiceImpl.userServiceImpl;
import service.userService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userServlet")
public class deleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user user =(user) request.getSession().getAttribute("user");
        int Id=user.getId();
        //生成user对象
        user user1=new user();
        user1.setId(Id);
        //生成执行删除的service对象
        userService service=new userServiceImpl();
        //执行删除操作
        service.delete(user1);
        ResultInfo info=new ResultInfo();
        info.setFlag(true);
        info.setMsg("删除成功");
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
}

}
