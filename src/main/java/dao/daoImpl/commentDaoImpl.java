package dao.daoImpl;
/**
 * @className:commentDaoImpl
 * @description:评论实现类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import JDBC_utils.JDBC_Utils1;
import dao.commentDao;
import domain.blog;
import domain.comment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class commentDaoImpl implements commentDao {
    private JdbcTemplate Template=new JdbcTemplate(JDBC_Utils1.getDataSource());

    @Override
    public List<comment> findAll()
    {
        String sql="SELECT * FROM comment";
        List<comment> commentAll= Template.query(sql, new BeanPropertyRowMapper<comment>(comment.class));
        return commentAll;

    }
    @Override
    public List<comment> findOne(blog blog1)
    {
        String sql="select * from comment where blogId=?";
        List<comment> comment2=  Template.query(sql,
                new BeanPropertyRowMapper<comment>(comment.class),
                blog1.getBlogsId());
        return comment2;
    }
    @Override
    public void delete (comment comment1)
    {
        String sql="delete from comment where commentId=?";
        Template.update(sql, comment1.getCommentId());
    }
    @Override
    public void insert (comment comment1)
    {
        String sql="insert into comment(content,blogId,userId) values(?,?,?)";
        Template.update(sql, comment1.getContent(),comment1.getBlogId(),comment1.getUserId());
    }
}
