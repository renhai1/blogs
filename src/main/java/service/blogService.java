package service;
/**
 * @className:blogService
 * @description:博客服务接口类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import domain.blog;
import domain.user;

import java.util.List;

public interface blogService {
    /**
     * @Author renhai
     * @Description  查询所有博客
     * @Date 18:36 2021/8/17
     * @Param []
     * @return java.util.List<domain.blog>
     **/
    public List<blog> findAll();
    /**
     * @Author renhai
     * @Description  查找个人博客
     * @Date 18:36 2021/8/17
     * @Param [user1]
     * @return java.util.List<domain.blog>
     **/
    public List<blog> findOne(user user1);
    /**
     * @Author renhai
     * @Description  删除一个博客
     * @Date 18:36 2021/8/17
     * @Param [blog1]
     * @return void
     **/
    public void delete (blog blog1);
    /**
     * @Author renhai
     * @Description  插入一个博客
     * @Date 18:36 2021/8/17
     * @Param [blog1]
     * @return void
     **/
    public void insert (blog blog1);
    /**
     * @Author renhai
     * @Description  审核博客
     * @Date 18:36 2021/8/17
     * @Param [blog1]
     * @return void
     **/
    public void check(blog blog1);
    /**
     * @Author renhai
     * @Description  按照热度排序
     * @Date 18:37 2021/8/17
     * @Param []
     * @return java.util.List<domain.blog>
     **/
    public List<blog> HOT();
/**
 * @Author renhai
 * @Description
 * @Date 14:56 2021/8/28
 * @Param []
 * @return java.util.List<domain.blog>
 **/
    public List<blog> FindAll();
    
    /**
     * @Author renhai
     * @Description  博客点赞
     * @Date 20:33 2021/8/28
     * @Param [blog1]
     * @return void
     **/
    public void BlogLike(blog blog1);
}
