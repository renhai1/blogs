package service.ServiceImpl;
/**
 * @className:noticeServiceImpl
 * @description:公告服务实现类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import dao.daoImpl.noticeDaoImpl;
import dao.noticeDao;
import domain.notice;
import service.noticeService;

import java.util.List;

public class noticeServiceImpl implements noticeService {
    private noticeDao dao=new noticeDaoImpl();
    @Override
    public List<notice> findAll() {
       return  dao.findAll();
    }

    @Override
    public notice findOne() {
        return  dao.findOne();
    }

    @Override
    public void insert(notice notice1) {
        dao.insert(notice1);
    }

    @Override
    public void delete(notice notice1) {
        dao.delete(notice1);
    }
}
