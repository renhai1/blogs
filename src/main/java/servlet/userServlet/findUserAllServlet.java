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
import java.util.List;

@WebServlet(name = "findUserAllServlet", value = "/findUserAllServlet")
public class findUserAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService service =new userServiceImpl();
        List<user> userAll=  service.findAll();
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(userAll);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
