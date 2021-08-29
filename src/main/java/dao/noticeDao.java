package dao;
/**
 * @className:noticeDao
 * @description:公告dao接口
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import domain.notice;

import java.util.List;

public interface noticeDao {
    /**
     * @Author renhai
     * @Description //查询所有公告
     * @Date 17:04 2021/8/17
     * @Param []
     * @return java.util.List<domain.notice>
     **/
    public List<notice> findAll();
    /**
     * @Author renhai
     * @Description //查询最新公告
     * @Date 17:04 2021/8/17
     * @Param []
     * @return domain.notice
     **/
    public notice findOne();
    /**
     * @Author renhai
     * @Description //插入一条公告 
     * @Date 17:04 2021/8/17
     * @Param [notice1]
     * @return void
     **/
    public void insert(notice notice1);

    /**
     * @Author renhai
     * @Description //删除一条公告
     * @Date 17:05 2021/8/17
     * @Param [notice1]
     * @return void
     **/
    public void delete(notice notice1);
}
