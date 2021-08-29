package servlet.noticeServlet;
/**
 * @className: NoticeFindOneServlet
 * @description:
 * @author: renhai
 * @create: 2021-08-17 15:41
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import service.ServiceImpl.noticeServiceImpl;
import service.noticeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "NoticeFindOneServlet", value = "/NoticeFindOneServlet")
public class NoticeFindOneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        noticeService service=new noticeServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(service.findOne());
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
