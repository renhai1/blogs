package dao.daoImpl;
/**
 * @className:noticeDaoImpl
 * @description:公告实现类
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import JDBC_utils.JDBC_Utils1;
import dao.noticeDao;
import domain.notice;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class noticeDaoImpl implements noticeDao {
    private JdbcTemplate Template=new JdbcTemplate(JDBC_Utils1.getDataSource());
    @Override
    public List<notice> findAll() {
        String sql="select * from notice";
        List<notice> notices=null;
        try
        {
            notices=Template.query(sql,new BeanPropertyRowMapper<notice>(notice.class));
        }catch (Exception e)
        {

        }
        return notices;
    }

    @Override
    public notice findOne() {
        String sql="SELECT * FROM notice WHERE DATETIME = (SELECT MAX(DATETIME) FROM notice)";
        notice notice1=null;
        try
        {
            notice1=Template.queryForObject(sql,new BeanPropertyRowMapper<notice>(notice.class));
        }catch (Exception e)
        {

        }
        return notice1;
    }

    @Override
    public void insert(notice notice1) {
        String sql="insert into notice(noticeTitle,noticeContent,datetime) values(?,?,?)";
        try
        {
            Template.update(sql, notice1.getNoticeTitle(),notice1.getNoticeContent(),notice1.getDateTime());
        }catch (Exception e)
        {

        }
    }

    @Override
    public void delete(notice notice1) {
        String sql="delete from notice where noticeId =?";
        Template.update(sql,notice1.getNoticeId());
    }
}
