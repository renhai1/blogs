package service;
/**
 * @className:userService
 * @description:用户管理的业务接口
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import domain.user;

import java.util.List;


public interface userService {
    /**
     * @Author renhai
     * @Description  查询所有用户
     * @Date 18:33 2021/8/17
     * @Param []
     * @return java.util.List<domain.user>
     **/
    public List<user> findAll();
    /**
     * @Author renhai
     * @Description  登录用户验证
     * @Date 18:33 2021/8/17
     * @Param [user1]
     * @return domain.user
     **/
    public user findOne(user user1);
    /**
     * @Author renhai
     * @Description  注册用户验证
     * @Date 18:34 2021/8/17
     * @Param [user1]
     * @return domain.user
     **/
    public user findOneRegister(user user1);
    /**
     * @Author renhai
     * @Description  删除一个用户
     * @Date 18:34 2021/8/17
     * @Param [user1]
     * @return void
     **/
    public void delete (user user1);
    /**
     * @Author renhai
     * @Description  插入一个用户
     * @Date 18:34 2021/8/17
     * @Param [user1]
     * @return void
     **/
    public void insert (user user1);
    /**
     * @Author renhai
     * @Description  修改一个用户
     * @Date 18:34 2021/8/17
     * @Param [user1]
     * @return void
     **/
    public void update (user user1);
    /**
     * @Author renhai
     * @Description  修改密码
     * @Date 18:35 2021/8/17
     * @Param [user1, pas]
     * @return void
     **/
    public void repeatPassword(user user1,String pas);
}
