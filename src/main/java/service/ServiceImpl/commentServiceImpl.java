package service.ServiceImpl;
/**
 * @className:commentServiceImpl
 * @description:评论服务实现类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import dao.commentDao;
import domain.blog;
import domain.comment;
import dao.daoImpl.commentDaoImpl;
import service.commentService;

import java.util.List;

public class commentServiceImpl implements commentService {

    @Override
    public List<comment> findAll() {
        commentDao comments=new commentDaoImpl();
        return comments.findAll();
    }

    @Override
    public List<comment> findOne(blog blog1) {
        commentDao comments=new commentDaoImpl();
        return comments.findOne(blog1);
    }

    @Override
    public void delete(comment comment1) {
        commentDao comments=new commentDaoImpl();
        comments.delete(comment1);

    }

    @Override
    public void insert(comment comment1) {
        commentDao comments=new commentDaoImpl();
        comments.insert(comment1);
    }
}
