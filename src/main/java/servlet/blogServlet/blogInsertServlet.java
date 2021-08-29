package servlet.blogServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.blog;
import domain.user;
import functionClass.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;
import service.ServiceImpl.blogServiceImpl;
import service.blogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
/*博客的发布功能*/
@WebServlet(name = "blogInsertServlet", value = "/blogInsertServlet")
public class blogInsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session获得用户id
        user user =(user) request.getSession().getAttribute("user");
        int userId=user.getId();
        //生成插入的对象
        Map<String,String[]> map=request.getParameterMap();
        blog blog1=new blog();
        try {
            BeanUtils.populate(blog1,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        blog1.setUserId(userId);
        blogService service =new blogServiceImpl();
        service.insert(blog1);
        //返回信息
        ResultInfo info=new ResultInfo();
        info.setFlag(true);
        info.setMsg("发布成功");
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }
}
