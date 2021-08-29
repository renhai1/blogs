package service;
/**
 * @className:noticeService
 * @description:公告服务接口类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import domain.notice;

import java.util.List;

public interface noticeService {
    /**
     * @Author renhai
     * @Description  查询所有公告
     * @Date 18:41 2021/8/17
     * @Param []
     * @return java.util.List<domain.notice>
     **/
    public List<notice> findAll();
    /**
     * @Author renhai
     * @Description  查询最新公告
     * @Date 18:42 2021/8/17
     * @Param []
     * @return domain.notice
     **/
    public notice findOne();
    /**
     * @Author renhai
     * @Description  插入一条公告
     * @Date 18:42 2021/8/17
     * @Param [notice1]
     * @return void
     **/
    public void insert(notice notice1);
    /**
     * @Author renhai
     * @Description  删除一条公告
     * @Date 18:42 2021/8/17
     * @Param [notice1]
     * @return void
     **/
    public void delete(notice notice1);
}
