package servlet.noticeServlet;
/**
 * @className: NoticeInsertServlet
 * @description:
 * @author: renhai
 * @create: 2021-08-17 15:42
 */

import domain.notice;
import org.apache.commons.beanutils.BeanUtils;
import service.ServiceImpl.noticeServiceImpl;
import service.noticeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "NoticeInsertServlet", value = "/NoticeInsertServlet")
public class NoticeInsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String[]> map=request.getParameterMap();
        notice notice1=new notice();
        try {
            BeanUtils.populate(notice1,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        noticeService service =new noticeServiceImpl();
        service.insert(notice1);
    }
}
