package dao.daoImpl;
/**
 * @className:blogsDaoImpl
 * @description:博客dao实现类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import JDBC_utils.JDBC_Utils1;
import dao.blogsDao;
import domain.blog;
import domain.user;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

public class blogsDaoImpl implements blogsDao {

    private JdbcTemplate Template=new JdbcTemplate(JDBC_Utils1.getDataSource());

    @Override
    public List<blog> findAll(){

        String sql="select * from blogs where status='Y'";
        List<blog> blogsAll=null;
        try
        {
            blogsAll= Template.query(sql, new BeanPropertyRowMapper<blog>(blog.class));
        }catch (Exception e)
        {

        }
        return blogsAll;
    }
    @Override
    public List<blog> findOne(user user1)
    {

        String sql="select * from blogs where userId=? and status='Y'";
        List<blog> blogAll =  null;
        try
        {
            blogAll= Template.query(sql,
                    new BeanPropertyRowMapper<blog>(blog.class),
                    user1.getId());
        }catch (Exception e)
        {

        }
        return blogAll;
    }
    @Override

    public void delete (blog blog1)
    {
        String sql="delete from blogs where blogsId=?";
        Template.update(sql,blog1.getBlogsId());
    }

    @Override

    public void insert (blog blog1)
    {
        String sql="insert into blogs(title,Content,userId,likeNumber) values(?,?,?,?)";
        Template.update(sql, blog1.getTitle(),blog1.getContent(),blog1.getUserId(),blog1.getLikeNumber());
    }

    @Override
    public void check(blog blog1)
    {
        String sql="update blogs set status='Y' where blogsId=?";
        Template.update(sql,blog1.getBlogsId());
    }
    @Override
    public List<blog> HOT(){
        String sql="select * from blogs where status='Y' ORDER BY likeNumber DESC";
        List<blog> HOTAll= Template.query(sql, new BeanPropertyRowMapper<blog>(blog.class));
        return HOTAll;
    }

    @Override
    public List<blog> FindAll() {

        String sql="select * from blogs";
        List<blog> blogsAll=null;
        try
        {
            blogsAll= Template.query(sql, new BeanPropertyRowMapper<blog>(blog.class));
        }catch (Exception e)
        {

        }
        return blogsAll;
    }

    @Override
    public blog FindOne(blog blog1) {
        String sql="select * from blogs where blogsId=?";
        blog blog2=null;
        try
        {
            blog2=Template.queryForObject(sql,new BeanPropertyRowMapper<blog>(blog.class),blog1.getBlogsId());
        }
        catch (Exception e)
        {

        }
        return blog2;
    }

    @Override
    public void update(blog blog1) {
        String sql ="update blogs set likeNumber=? where blogsId=?";
        try
        {
            Template.update(sql,(blog1.getLikeNumber()+1),blog1.getBlogsId());
        }catch (Exception e)
        {

        }
    }
}
