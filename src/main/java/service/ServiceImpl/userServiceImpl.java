package service.ServiceImpl;
/**
 * @className:userServiceImpl
 * @description:用户功能实现类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import dao.userDao;
import domain.user;
import dao.daoImpl.userDaoImpl;
import service.userService;

import java.util.List;

public class userServiceImpl implements userService {
    @Override
    public List<user> findAll() {
        userDao users=new userDaoImpl();
        return users.findAll();
    }
    @Override
    public user findOne(user user1)
    {
        userDao users=new userDaoImpl();
        return users.findOne(user1);
    }
    @Override
    public user findOneRegister(user user1)
    {
        userDao users=new userDaoImpl();
       user user2= users.findOneRegister(user1);
        return user2;
    }
    @Override
    public void delete (user user1)
    {
        userDao users=new userDaoImpl();
        users.delete(user1);
    }
    @Override
    public void insert (user user1)
    {
        userDao users=new userDaoImpl();
        users.insert(user1);
    }
    @Override
    public void update (user user1)
    {
        userDao users=new userDaoImpl();
        users.update(user1);
    }
    @Override
    public void repeatPassword(user user1,String pas)
    {
        userDao users=new userDaoImpl();
        users.repeatPassword(user1,pas);
    }
}
