package servlet.userServlet;

import domain.user;
import service.ServiceImpl.userServiceImpl;
import service.userService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "repeatPasswordServlet", value = "/repeatPasswordServlet")
public class repeatPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user user1=(user)request.getSession().getAttribute("user");
        String pas=user1.getPas();
        user user2=new user();
        user2.setId(user1.getId());
        user2.setPas(request.getParameter("password"));
        userService service =new userServiceImpl();
        service.repeatPassword(user2,pas);
    }
}
