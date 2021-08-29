package functionClass;
/**
 * @className:photoSave
 * @description:图片读取工具类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
public class photoSave {
    /**
     * @Author renhai
     * @Description  获取输入流
     * @Date 20:10 2021/8/17
     * @Param [path]
     * @return java.io.FileInputStream
     **/
    public static FileInputStream readImages(String path) throws Exception {
        return new FileInputStream(new File(path));
    }

    /**
     * @Author renhai
     * @Description  读取表中图片获取输出流
     * @Date 21:06 2021/8/17
     * @Param [in, targetPath]
     * @return void
     **/
    public static void readImage1(InputStream in, String targetPath) {
        File file = new File(targetPath);
        String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
        if (!file.exists()) {
            new File(path).mkdir();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf))!= -1) {
                fos.write(buf, 0, len);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            if(fos!=null) {
                try {
                    fos.close();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }
            }
        }
    }
}
