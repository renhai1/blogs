package service.ServiceImpl;
/**
 * @className:blogServiceImpl
 * @description:博客服务实现类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import dao.blogsDao;
import domain.blog;
import dao.daoImpl.blogsDaoImpl;
import domain.user;
import service.blogService;

import java.util.List;

public class blogServiceImpl implements blogService {
    @Override
    public List<blog> findAll() {
        blogsDao blogs=new blogsDaoImpl();
        return blogs.findAll();
    }

    @Override
    public List<blog> findOne(user user1) {
        blogsDao blogs=new blogsDaoImpl();
        return blogs.findOne(user1);
    }

    @Override
    public void delete(blog blog1) {
        blogsDao blogs=new blogsDaoImpl();
       blogs.delete(blog1);
    }

    @Override
    public void insert(blog blog1) {
        blogsDao blogs=new blogsDaoImpl();
        blogs.insert(blog1);
    }

    @Override
    public void check(blog blog1)
    {
        blogsDao blogs=new blogsDaoImpl();
        blogs.check(blog1);
    }

    @Override
    public List<blog> HOT() {
        blogsDao blogs=new blogsDaoImpl();
        return blogs.HOT();
    }

    @Override
    public List<blog> FindAll() {
        blogsDao blogs=new blogsDaoImpl();
        return blogs.FindAll();
    }

    @Override
    public void BlogLike(blog blog1) {
        blogsDao dao=new blogsDaoImpl();
        blog blog2=dao.FindOne(blog1);
        dao.update(blog2);
    }
}
