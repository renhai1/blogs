package servlet;
/**
 * @className:NoticeServlet
 * @description:
 * @author: renhai
 * @create: 2021-08-18 17:49
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.notice;
import functionClass.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;
import service.ServiceImpl.noticeServiceImpl;
import service.noticeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/Notice/*")
public class NoticeServlet extends BaseServlet {
    /**
     * @Author renhai
     * @Description  删除一条公告
     * @Date 19:20 2021/8/18
     * @Param [request, response]
     * @return void
     **/
    public void Delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int noticeId=Integer.parseInt(request.getParameter("noticeId"));
        notice notice1=new notice();
        notice1.setNoticeId(noticeId);
        noticeService service =new noticeServiceImpl();
        service.delete(notice1);
    }
/**
 * @Author renhai
 * @Description  返回所有公告
 * @Date 19:21 2021/8/18
 * @Param [request, response]
 * @return void
 **/
    public void FindAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        noticeService service=new noticeServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(service.findAll());
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
    /**
     * @Author renhai
     * @Description  返回最新公告
     * @Date 19:21 2021/8/18
     * @Param [request, response]
     * @return void
     **/
    public void FindOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        noticeService service=new noticeServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(service.findOne());
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
    /**
     * @Author renhai
     * @Description  插入一条公告
     * @Date 19:21 2021/8/18
     * @Param [request, response]
     * @return void
     **/
    public void Insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String[]> map=request.getParameterMap();
        notice notice1=new notice();
        try {
            BeanUtils.populate(notice1,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=time.format(new Date());
        notice1.setDateTime(date);
        noticeService service =new noticeServiceImpl();
        service.insert(notice1);
        ResultInfo info=new ResultInfo();
        info.setFlag(true);
        info.setMsg("发布成功");
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(info);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
