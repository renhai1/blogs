package dao.daoImpl;
/**
 * @className:userDaoImpl
 * @description:用户dao实现类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import JDBC_utils.JDBC_Utils1;
import dao.userDao;
import domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
public class userDaoImpl implements userDao {
    private JdbcTemplate Template=new JdbcTemplate(JDBC_Utils1.getDataSource());

    @Override
    public List<user> findAll(){

        String sql="select * from user where status= 'Y'";
        List<user> usersAll=null;
        try{
            usersAll=  Template.query(sql, new BeanPropertyRowMapper<user>(user.class));
        }catch (Exception e)
        {

        }
       return usersAll;
    }

    @Override
    public user findOne(user user1)
    {
        String sql="select * from user where email = ? and pas=? and status='Y'";
        user User=null;
        try{
            User= Template.queryForObject(sql,
                    new BeanPropertyRowMapper<user>(user.class),
                    user1.getEmail(),user1.getPas());
        }catch (Exception e)
        {

        }
        return User;
    }
    @Override
    public user findOneRegister(user user1)
    {
        String sql="select * from user where email=?";
        user User=null;
        try
        {
            User= Template.queryForObject(sql,
                    new BeanPropertyRowMapper<user>(user.class),
                    user1.getEmail());
        }catch (Exception e)
        {

        }

        return User;
    }
    @Override
    public void delete (user user1)
    {
        String sql="delete from user where Id = ?";
        Template.update(sql, user1.getId());
    }
    @Override
    public void insert (user user1)
    {
        String sql="insert into user(Name,pas,email,status) values(?,?,?,?)";
        Template.update(sql,user1.getName(),user1.getPas(),user1.getEmail(),user1.getStatus());
    }
    @Override
    public void update (user user1)
    {
        String sql="update user set photoAddress=? where Id=?";
        Template.update(sql,user1.getPhotoAddress(),user1.getId());
    }
    @Override
    public void repeatPassword(user user1 ,String pas) {

        String sql="update user set pas=? where Id=? and pas=?";
        Template.update(sql, user1.getPas(),user1.getId(),pas);
    }
}
