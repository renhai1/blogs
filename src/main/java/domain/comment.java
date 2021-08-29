package domain;
/**
 * @className:comment
 * @description:评论对象生成
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
public class comment {
    private int commentId;
    private String content;
    private int blogId;
    private int userId;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        return "comment{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", blogId=" + blogId +
                ", userId=" + userId +
                '}';
    }
}
