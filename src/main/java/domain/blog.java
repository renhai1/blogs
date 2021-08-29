package domain;
/**
 * @className:blog
 * @description: 构建与数据库表对应的blog对象
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
public class blog {
    private int blogsId;
    private String title;
    private String Content;
    private int userId;
    private  int likeNumber;
    private String status;

    public int getBlogsId() {
        return blogsId;
    }

    public void setBlogsId(int blogsId) {
        this.blogsId = blogsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
/**
 * @Author renhai
 * @Description 讲对象转成字符串编于测试代码
 * @Date 16:59 2021/8/17
 * @Param []
 * @return java.lang.String
 **/
    @Override
    public String toString() {
        return "blog{" +
                "blogsId=" + blogsId +
                ", title='" + title + '\'' +
                ", Content='" + Content + '\'' +
                ", userId=" + userId +
                ", likeNumber=" + likeNumber +
                ", status='" + status + '\'' +
                '}';
    }
}
