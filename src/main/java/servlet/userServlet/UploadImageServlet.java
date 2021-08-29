package servlet.userServlet;
/**
 * @className:${NAME}
 * @description:
 * @author: renhai
 * @create: 2021-08-18 19:38
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UploadImageServlet", value = "/UploadImageServlet")
public class UploadImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
