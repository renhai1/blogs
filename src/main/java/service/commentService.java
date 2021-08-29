package service;
/**
 * @className:commentService
 * @description:评论服务接口类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import domain.blog;
import domain.comment;

import java.util.List;

public interface commentService {
    /**
     * @Author renhai
     * @Description  查询所有评论
     * @Date 18:38 2021/8/17
     * @Param []
     * @return java.util.List<domain.comment>
     **/
    public List<comment> findAll();
    /**
     * @Author renhai
     * @Description  查找某个博客评论
     * @Date 18:38 2021/8/17
     * @Param [blog1]
     * @return java.util.List<domain.comment>
     **/
    public List<comment> findOne(blog blog1);
    /**
     * @Author renhai
     * @Description  删除一个评论
     * @Date 18:38 2021/8/17
     * @Param [comment1]
     * @return void
     **/
    public void delete (comment comment1);
    /**
     * @Author renhai
     * @Description  插入一个评论
     * @Date 18:41 2021/8/17
     * @Param [comment1]
     * @return void
     **/
    public void insert (comment comment1);
}
