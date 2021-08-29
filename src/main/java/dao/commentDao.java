package dao;
/**
 * @className:commentDao
 * @description:评论dao接口类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import domain.blog;
import domain.comment;

import java.util.List;

public interface commentDao {
    /**
     * @Author renhai
     * @Description //TODO 查询所有评论
     * @Date 17:15 2021/8/17
     * @Param []
     * @return java.util.List<domain.comment>
     **/
    public List<comment> findAll();
    /**
     * @Author renhai
     * @Description //TODO 查找某个博客的评论
     * @Date 17:15 2021/8/17
     * @Param [blog1]
     * @return java.util.List<domain.comment>
     **/
    public List<comment> findOne(blog blog1);
    /**
     * @Author renhai
     * @Description //TODO 删除一个评论
     * @Date 17:15 2021/8/17
     * @Param [comment1]
     * @return void
     **/
    public void delete (comment comment1);
    /**
     * @Author renhai
     * @Description //TODO 插入一个评论
     * @Date 17:16 2021/8/17
     * @Param [comment1]
     * @return void
     **/
    public void insert (comment comment1);
}
