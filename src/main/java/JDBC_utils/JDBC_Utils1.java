package JDBC_utils;

/**
 * @className:JDBC_Utils1
 * @description:数据库连接池
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC_Utils1 {
      private static  DataSource ds;
      static {
          try {
              Properties pro=new Properties();
              InputStream in=JDBC_Utils1.class.getClassLoader().getResourceAsStream("druid.properties");
              pro.load(in);
              ds= DruidDataSourceFactory.createDataSource(pro);
          }catch (Exception e) {
              e.printStackTrace();
          }
      }
    public static DataSource getDataSource()
    {
        return ds;
    }
      public static Connection getConnection() throws SQLException {
          return ds.getConnection();
      }
      public static void close(Statement stmt,Connection conn)
      {
          if(stmt!=null)
      {
          try {
              stmt.close();
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }
      }
          if(conn!=null)
          {
              try {
                  conn.close();
              } catch (SQLException throwables) {
                  throwables.printStackTrace();
              }
          }
      }
    public static void close(ResultSet rs, Statement stmt, Connection conn)
    {
        if(rs!=null)
        {
            try{
                rs.close();
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        if(stmt!=null)
        {
            try{
                stmt.close();
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        if(conn!=null)
        {
            try{
                conn.close();
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
