package servlet;
/**
 * @className:BlogServlet
 * @description:博客servlet
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.blog;
import domain.user;
import functionClass.ResultInfo;
import service.ServiceImpl.blogServiceImpl;
import service.blogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(  "/Blog/*")
public class BlogServlet extends BaseServlet {
/**
 * @Author renhai
 * @Description  博客按照点赞排序
 * @Date 19:18 2021/8/18
 * @Param [request, response]
 * @return void
 **/
    public void Hot(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        blogService service =new blogServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(service.HOT());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
/**
 * @Author renhai
 * @Description  插入一条博客
 * @Date 19:19 2021/8/18
 * @Param [request, response]
 * @return void
 **/
    public void Insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session获得用户id
        user user1 =(user) request.getSession().getAttribute("user");
        int userId=user1.getId();
        //生成插入的对象
        blog blog1=new blog();
        blog1.setTitle(request.getParameter("title"));
        blog1.setContent(request.getParameter("Content"));
        blog1.setLikeNumber(Integer.parseInt(request.getParameter("likeNumber")));
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
    /**
     * @Author renhai
     * @Description  审核一条博客
     * @Date 19:19 2021/8/18
     * @Param [request, response]
     * @return void
     **/
    public void Check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId=Integer.parseInt(request.getParameter("blogId"));
        blog blog1=new blog();
        blog1.setBlogsId(blogId);
        blogService service =new blogServiceImpl();
        service.check(blog1);
    }
    /**
     * @Author renhai
     * @Description  删除一条博客
     * @Date 19:19 2021/8/18
     * @Param [request, response]
     * @return void
     **/
    public void Delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId= Integer.parseInt(request.getParameter("blogId"));
        blog blog1=new blog();
        blog1.setBlogsId(blogId);
        blogService service =new blogServiceImpl();
        service.delete(blog1);
        //返回信息
        ResultInfo info=new ResultInfo();
        info.setFlag(true);
        info.setMsg("删除成功");
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
    /**
     * @Author renhai
     * @Description  查找所有博客
     * @Date 19:19 2021/8/18
     * @Param [request, response]
     * @return void
     **/
    public void FindAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        blogService service =new blogServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(service.findAll());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
    /**
     * @Author renhai
     * @Description  查找一条博客
     * @Date 19:19 2021/8/18
     * @Param [request, response]
     * @return void
     **/
    public void FindOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中得到用户ID
        user user =(user) request.getSession().getAttribute("user");
        int userId=user.getId();
        user user1=new user();
        user1.setId(userId);
        blogService service =new blogServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(service.findOne(user1));
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
    /**
     * @Author renhai
     * @Description  后台所有博客查询
     * @Date 14:58 2021/8/28
     * @Param [request, response]
     * @return void
     **/
    public void BlogsAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        blogService service =new blogServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        String json=  mapper.writeValueAsString(service.FindAll());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
    /**
     * @Author renhai
     * @Description  更新点赞数
     * @Date 20:30 2021/8/28
     * @Param [request, response]
     * @return void
     **/
    public void BlogsLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int blogId=Integer.parseInt(request.getParameter("blogId"));
        blog blog1=new blog();
        blog1.setBlogsId(blogId);
        blogService service =new blogServiceImpl();
        service.BlogLike(blog1);
    }


}
