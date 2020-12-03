package com.example.commoncenter.util.page;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author : Bei-Zhen
 * @date : 2020-08-24 0:43
 */
public class JDBC2 {

//static int count = 0;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        conn();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start)/1000 + "秒");
    }

    public static void conn(){
//1.导入驱动jar包
//2.注册驱动(mysql5之后的驱动jar包可以省略注册驱动的步骤)
//Class.forName("com.mysql.jdbc.Driver");
//3.获取数据库连接对象
        Connection conn = null;
        PreparedStatement pstmt = null;
        {
            try {
//"&rewriteBatchedStatements=true",一次插入多条数据，只插入一次
                conn = DriverManager.getConnection("jdbc:mysql://47.96.101.188:3306/suty?" + "","root","123456");
//4.定义sql语句
                String sql = "insert into test_page(username,password) values(?,?)";
//5.获取执行sql的对象PreparedStatement
                pstmt = conn.prepareStatement(sql);
//6.不断产生sql
                for (int i = 0; i < 100000; i++) {
                    pstmt.setString(1,(int)(Math.random()*1000000)+"");
                    pstmt.setString(2,(int)(Math.random()*1000000)+"");
                    pstmt.addBatch();
                }
//7.往数据库插入一次数据
                pstmt.executeBatch();
                System.out.println("添加1000000条信息成功！");

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
//8.释放资源
//避免空指针异常
                if(pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if(conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
