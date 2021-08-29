package servlet.userServlet;
/**
 * @className:FileServlet
 * @description:文件上传
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import functionClass.ResultInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
public class FileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        //判断上传的表单是普通表单还是带文件的表单，是返回true,否返回false;

        if (!ServletFileUpload.isMultipartContent(request)){
            return;//普通文件直接返回
        }

        //创建上传文件的保存目录，为了安全建议在WEB-INF目录下，用户无法访问

        //获取上传文件的保存路径
        String uploadpath = this.getServletContext().getRealPath("image/Upload");
        File uploadfile = new File(uploadpath);
        if (!uploadfile.exists()){
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
            String msg = uploadParseRequest(upload,request,uploadpath);
            //Servlet请求转发消息
            ResultInfo info=new ResultInfo();
            info.setFlag(true);
            info.setMsg(msg);
            ObjectMapper mapper=new ObjectMapper();
            String json=mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
        }catch (FileUploadException e){
            e.printStackTrace();
        }
    }

    public static DiskFileItemFactory gteDiskFileItemFactory(File file){
        //1、创建DiskFileItemFactory对象，处理文件上传路径或限制文件大小
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //通过这个工厂设置一个缓冲区，当上传的文件大小大于缓冲区的时候，将它放到临时文件中；
        factory.setSizeThreshold(1024 * 1024);//缓冲区大小为1M
        factory.setRepository(file);
        return factory;
    }

    public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory){
        //2、获取ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);
        //监听文件上传进度
        upload.setProgressListener(new ProgressListener() {
            @Override
            public void update(long pBytesRead, long lpContentLenght, int i) {
                //pBytesRead:已读取到的文件大小
                //pContentLenght：文件大小
                //System.out.println("总大小："+lpContentLenght+"已上传："+pBytesRead);
            }
        });

        //处理乱码问题
        upload.setHeaderEncoding("UTF-8");
        //设置单个文件的最大值
        upload.setFileSizeMax(1024 * 1024 * 10);
        //设置总共能够上传文件的大小
        //1024 = 1kb * 1024 = 1M * 10 = 10M
        upload.setSizeMax(1024 * 1024 * 10);
        return upload;
    }

    public static String uploadParseRequest(ServletFileUpload upload,HttpServletRequest request,String uploadpath) throws IOException, FileUploadException {
            String msg = "";
            //3、处理上传文件
            //把前端的请求解析，封装成一个FileItem对象
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                //判断是普通表单还是带文件的表单
                if (fileItem.isFormField()){
                    //getFieldName指的是前端表单控件的name

                    String name = fileItem.getFieldName();

                    //处理乱码
                    String value = fileItem.getString("UTF-8");

                   // System.out.println(name+":"+value);
                }else {
                    //判断它是带文件的表单
                    //======================处理文件=======================//
                    //拿到文件的名字
                    String uploadFileName = fileItem.getName();
                    //System.out.println("上传的文件名："+uploadFileName);

                    if (uploadFileName.trim().equals("") || uploadFileName == null){
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
                   // System.out.println("文件信息【文件名："+fileName+"文件类型："+fileExtName+"】");

                    //可以使用UUID(唯一通用识别码)来保证文件名的统一
                    String uuidFileName = UUID.randomUUID().toString();


                    //=======================传输文件=========================//
                    //获得文件上传的流
                    InputStream inputStream = fileItem.getInputStream();

                    //创建一个文件输出流
                    FileOutputStream fos = new FileOutputStream(uploadpath + "/" + uuidFileName +"."+ fileExtName);

                    //创建一个缓冲区
                    byte[] buffer = new byte[1024 * 1024];

                    //判断是否读取完毕
                    int len = 0;

                    //如果大于0，说明还存在数据
                    while ((len=inputStream.read(buffer))>0){
                        fos.write(buffer,0,len);
                    }

                    //关闭流
                    fos.close();
                    inputStream.close();
                    //返回图片路径给前端展示
                    msg =uploadpath + "/" + uuidFileName +"."+ fileExtName;
                    fileItem.delete();//上传成功，清除临时文件
                }
            }
            return msg;
        }
    }