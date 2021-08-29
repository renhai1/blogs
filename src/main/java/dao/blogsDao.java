package dao;
/**
 * @className:blogsDao
 * @description:博客dao接口
 * @author: renhai
 * @create: 2021-08-17 16:58
 */

import domain.blog;
import domain.user;

import java.util.List;

public interface blogsDao {
    /**
     * @return java.util.List<domain.blog>
     * @Author renhai
     * @Description //TODO 查询所有博客
     * @Date 17:12 2021/8/17
     * @Param []
     **/
    public List<blog> findAll();

    /**
     * @return java.util.List<domain.blog>
     * @Author renhai
     * @Description //TODO 查找个人博客
     * @Date 17:13 2021/8/17
     * @Param [user1]
     **/
    public List<blog> findOne(user user1);

    /**
     * @return void
     * @Author renhai
     * @Description //TODO 删除一个博客
     * @Date 17:13 2021/8/17
     * @Param [blog1]
     **/
    public void delete(blog blog1);

    /**
     * @return void
     * @Author renhai
     * @Description //TODO 插入一条博客
     * @Date 17:13 2021/8/17
     * @Param [blog1]
     **/
    public void insert(blog blog1);

    /**
     * @return void
     * @Author renhai
     * @Description //TODO 审核博客
     * @Date 17:13 2021/8/17
     * @Param [blog1]
     **/
    public void check(blog blog1);

    /**
     * @return java.util.List<domain.blog>
     * @Author renhai
     * @Description //TODO 博客按照热度排序0
     * @Date 17:14 2021/8/17
     * @Param []
     **/
    public List<blog> HOT();

    /**
     * @Author renhai
     * @Description 后台查询所有博客
     * @Date 14:56 2021/8/28
     * @Param []
     * @return java.util.List<domain.blog>
     **/
    public List<blog> FindAll();
    
    /**
     * @Author renhai
     * @Description  查询一个博客
     * @Date 20:34 2021/8/28
     * @Param [blog1]
     * @return domain.blog
     **/
    public blog FindOne(blog blog1);

    /**
     * @Author renhai
     * @Description  更新点赞数
     * @Date 20:37 2021/8/28
     * @Param [blog1]
     * @return void
     **/
    public void update(blog blog1);
}
