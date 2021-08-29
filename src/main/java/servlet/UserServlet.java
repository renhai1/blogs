package servlet;

/**
 * @className:UserServlet
 * @description:用户servlet
 * @author: renhai
 * @create: 2021-08-17 16:58
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.user;
import functionClass.ResultInfo;
import functionClass.codeMake;
import functionClass.sendEmail;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.ServiceImpl.userServiceImpl;
import service.userService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    /**
     * @return void
     * @Author renhai
     * @Description 生成和获得验证码
     * @Date 17:54 2021/8/18
     * @Param [request, response]
     **/
    public void GetCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用我们的验证码类，生成验证码类对象
        codeMake ivc = new codeMake();
        //获取验证码
        BufferedImage image = ivc.getImage();
        //将验证码的文本存在session中
        request.getSession().setAttribute("codeText", ivc.getText());
        //将验证码图片响应给客户端
        codeMake.output(image, response.getOutputStream());
    }

    /**
     * @return void
     * @Author renhai
     * @Description 登录验证
     * @Date 17:54 2021/8/18
     * @Param [request, response]
     **/
    public void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //从session中获取真正的验证码
        String session_code = (String) request.getSession().getAttribute("codeText");
        //防止验证码复用
        request.removeAttribute("codeText");
        /*先验证验证码，再验证用户是否存在*/
        //获得用户输入的验证码
        String userCode = request.getParameter("captcha");
        //返回前端的对象
        ResultInfo info = new ResultInfo();
        if (!(userCode.equalsIgnoreCase(session_code))) {
            info.setFlag(false);
            info.setMsg("验证码错误");
        } else {

        String email = request.getParameter("email");
        String pas = request.getParameter("password");
            //生成登录对象
            user user1 = new user();
            user1.setEmail(email);
            user1.setPas(pas);
            //验证登录对象
            userService userService = new userServiceImpl();
            user user2 = userService.findOne(user1);
            if (user2 != null) {
                //账号密码正确
                info.setFlag(true);
                info.setData(user2);
                info.setMsg("登录成功");
                //将用户存在session中 用于确定个人博客
                request.getSession().setAttribute("user", user2);
            } else {
                //账号或密码错误
                info.setFlag(false);
                info.setMsg("账号或密码错误");
            }
        }
        //返回信息给前端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * @return void
     * @Author renhai
     * @Description 查找所有的用户
     * @Date 17:54 2021/8/18
     * @Param [request, response]
     **/
    public void FindAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService service = new userServiceImpl();
        List<user> userAll = service.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userAll);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * @return void
     * @Author renhai
     * @Description 注册验证
     * @Date 17:54 2021/8/18
     * @Param [request, response]
     **/
    public void Register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取真正的验证码
        String session_code = (String) request.getSession().getAttribute("codeText");
        //防止验证码复用
        request.removeAttribute("codeText");
        /*先验证验证码，再验证用户是否存在*/
        //获得用户输入的验证码
        String userCode = request.getParameter("captcha");
        //返回前端的对象
        ResultInfo info = new ResultInfo();

        if (!(userCode.equalsIgnoreCase(session_code))) {
            //验证码错误
            info.setFlag(false);
            info.setMsg("code wrong");
        } else {
            //验证码正确
            //得到真正的邮箱验证码
            String status = (String) request.getSession().getAttribute("checkCode");
            //清除旧的邮箱验证码
            request.removeAttribute("checkCode");
            //获得注册的对象
            user user1 = new user();
            user1.setName(request.getParameter("userName"));
            user1.setPas(request.getParameter("password"));
            user1.setEmail(request.getParameter("email"));
            user1.setCode(request.getParameter("code"));
            String code=user1.getCode();
            //检测是否用户存在
            userService userService = new userServiceImpl();
            user user2 = userService.findOneRegister(user1);
            if (user2 != null) {
                //非空，用户存在
                info.setFlag(false);
                info.setMsg("用户已存在");
            } else {
                //用户不存在
                if (!(code.equalsIgnoreCase(status))) {
                    info.setFlag(false);
                    info.setMsg("邮箱验证码错误");
                } else {
                    user1.setStatus("Y");
                    userService.insert(user1);
                    user user3 = userService.findOneRegister(user1);
                    info.setFlag(true);
                    info.setMsg("注册成功，账号为：" + user3.getId());
                }
            }
        }
        //返回信息给前端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * @return void
     * @Author renhai
     * @Description 删除用户
     * @Date 17:54 2021/8/18
     * @Param [request, response]
     **/
    public void Delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int Id = Integer.parseInt(request.getParameter("userId"));
        //生成user对象
        user user1 = new user();
        user1.setId(Id);
        //生成执行删除的service对象
        userService service = new userServiceImpl();
        //执行删除操作
        service.delete(user1);
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("删除成功");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * @return void
     * @Author renhai
     * @Description 退出登录
     * @Date 17:54 2021/8/18
     * @Param [request, response]
     **/
    public void Exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁session
        request.getSession().invalidate();
    }

    /**
     * @return void
     * @Author renhai
     * @Description 重置密码
     * @Date 17:54 2021/8/18
     * @Param [request, response]
     **/
    public void RepeatPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user user1 = (user) request.getSession().getAttribute("user");
        String pas = user1.getPas();
        user user2 = new user();
        user2.setId(user1.getId());
        user2.setPas(request.getParameter("password"));
        userService service = new userServiceImpl();
        service.repeatPassword(user2, pas);
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("修改密码成功");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * @return void
     * @Author renhai
     * @Description 发生邮箱验证码
     * @Date 17:54 2021/8/18
     * @Param [request, response]
     **/
    public void EmailSend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        System.out.println(email);
        sendEmail send = new sendEmail();
        try {
            request.getSession().setAttribute("checkCode", send.send(email));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @Author renhai
     * @Description 更新用户信息
     * @Date 17:54 2021/8/18
     * @Param [request, response]
     **/
    public void UpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中得到用户ID
        user user = (user) request.getSession().getAttribute("user");
        int Id = user.getId();
        String Name = request.getParameter("username");
        //封装生成对象
        user user1 = new user();
        user1.setId(Id);
        user1.setName(Name);
        //执行更新操作
        userService service = new userServiceImpl();
        service.update(user1);

        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("修改成功");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * @return void
     * @Author renhai
     * @Description 上传图片
     * @Date 19:42 2021/8/18
     * @Param [request, response]
     **/
    public void UploadImage(HttpServletRequest request, HttpServletResponse response) {
        
        //判断上传的表单是普通表单还是带文件的表单，是返回true,否返回false;
        if (!ServletFileUpload.isMultipartContent(request)) {
            return;//普通文件直接返回
        }

        //创建上传文件的保存目录，为了安全建议在WEB-INF目录下，用户无法访问

        //获取上传文件的保存路径
        String uploadpath = this.getServletContext().getRealPath("/image/headPhoto");
        File uploadfile = new File(uploadpath);
        if (!uploadfile.exists()) {
            uploadfile.mkdir();//如果目录不存在就创建这样一个目录
        }

        /*临时文件
        //临时路径，如果上传的文件超过预期的大小，我们将它存放到一个临时目录中，过几天自动删除，或者提醒用户转存为永久
//        String tmppath = this.getServletContext().getRealPath("WEB-INF/tmp");
//        File file = new File(tmppath);
//        if (!file.exists()){
//            file.mkdir();//如果目录不存在就创建这样临时目录
//        }
        //处理上传的文件一般需要通过流来获取，我们可以通过request.getInputstream(),原生态文件上传流获取，十分麻烦
        //但是我们都建议使用Apache的文件上传组件来实现，common-fileupload,它需要依赖于common-io组件；
        *
         */

        try {
            //1、创建DiskFileItemFactory对象，处理文件上传路径或限制文件大小
            DiskFileItemFactory factory = gteDiskFileItemFactory(uploadfile);
            //2、获取ServletFileUpload
            ServletFileUpload upload = getServletFileUpload(factory);
            //3、处理上传文件
            String msg = uploadParseRequest(upload, request, uploadpath);
            //4.存头像地址到数据库
            user user0 = (user) request.getSession().getAttribute("user");
            user user1=new user();
            user1.setId(user0.getId());
            user1.setPhotoAddress(msg);
            userService service=new userServiceImpl();
            service.update(user1);
            //Servlet请求转发消息
            ResultInfo info = new ResultInfo();
            info.setFlag(true);
            info.setMsg("上传成功");
            info.setData(user1);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
        } catch (FileUploadException | IOException e) {
            e.printStackTrace();
        }
    }

    public static DiskFileItemFactory gteDiskFileItemFactory(File file) {
        //1、创建DiskFileItemFactory对象，处理文件上传路径或限制文件大小
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //通过这个工厂设置一个缓冲区，当上传的文件大小大于缓冲区的时候，将它放到临时文件中；
        factory.setSizeThreshold(1024 * 1024);//缓冲区大小为1M
        factory.setRepository(file);
        return factory;
    }

    public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
        //2、获取ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);
        //监听文件上传进度
//        upload.setProgressListener(new ProgressListener() {
//            @Override
//            public void update(long pBytesRead, long lpContentLenght, int i) {
////                pBytesRead:已读取到的文件大小
////                pContentLenght：文件大小
//                System.out.println("总大小："+lpContentLenght+"已上传："+pBytesRead);
//            }
//        }
//        );

        //处理乱码问题
        upload.setHeaderEncoding("UTF-8");
        //设置单个文件的最大值
        upload.setFileSizeMax(1024 * 1024 * 10);
        //设置总共能够上传文件的大小
        //1024 = 1kb * 1024 = 1M * 10 = 10M
        upload.setSizeMax(1024 * 1024 * 10);
        return upload;
    }

    public static String uploadParseRequest(ServletFileUpload upload, HttpServletRequest request, String uploadpath) throws IOException, FileUploadException {
        String msg = "cqclqn0t.dongtaiyuming.net/image/headPhoto";
        //3、处理上传文件
        //把前端的请求解析，封装成一个FileItem对象
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            //判断是普通表单还是带文件的表单
            if (fileItem.isFormField()) {
                //getFieldName指的是前端表单控件的name

                String name = fileItem.getFieldName();

                //处理乱码
                String value = fileItem.getString("UTF-8");

                 //System.out.println(name+":"+value);
            } else {
                //判断它是带文件的表单
                //======================处理文件=======================//
                //拿到文件的名字
                String uploadFileName = fileItem.getName();

                //System.out.println("上传的文件名："+uploadFileName);

                if (uploadFileName.trim().equals("") || uploadFileName == null) {
                    continue;
                }

                //获得上传的文件名，例如/img/girl/ooa.jpg,只需要ooa，其前面的后面的都不需要
                String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                //获得文件的后缀名
                String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
                /*
                 * 如果后缀名 fileExtName 不是我们需要的
                 *就直接return，不处理，告诉用户类型不对
                 * */
                 //System.out.println("文件信息【文件名："+fileName+"文件类型："+fileExtName+"】");

                //可以使用UUID(唯一通用识别码)来保证文件名的统一
                String uuidFileName = UUID.randomUUID().toString();


                //=======================传输文件=========================//
                //获得文件上传的流
                InputStream inputStream = fileItem.getInputStream();

                //创建一个文件输出流
                FileOutputStream fos = new FileOutputStream(uploadpath + "/" + uuidFileName + "." + fileExtName);

                //创建一个缓冲区
                byte[] buffer = new byte[1024 * 1024];

                //判断是否读取完毕
                int len = 0;

                //如果大于0，说明还存在数据
                while ((len = inputStream.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                //关闭流
                fos.close();
                inputStream.close();
                //返回图片路径给前端展示
                msg =msg+"/" + uuidFileName + "." + fileExtName;
                fileItem.delete();//上传成功，清除临时文件
            }
        }
        return msg;
    }
}

