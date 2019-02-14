package com.it18zhang.jdbcdemo.test;

import org.junit.Test;

import java.sql.*;

/**
 * 测试隔离级别
 */
public class TestIsolationLevel {

    /**
     * 执行写，不提交
     */
    @Test
    public void testA() throws  Exception{
        //创建连接
        String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/big4";
        String username = "root";
        String password = "root";
        Class.forName(driverClass);

        //此处断点，等待B事务操作
        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);


        Statement st = conn.createStatement();
        st.execute("update users set age = 80 where id = 1");



        System.out.println("===============");
        conn.commit();
        conn.close();
    }

    /**
     * 查询，脏读查到别人没有提交的数据
     */
    @Test
    public void testB() throws  Exception{
        //创建连接
        String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/big4";
        String username = "root";
        String password = "root";
        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(url, username, password);

        //设置隔离级别读未提交==>导致脏读
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        conn.setAutoCommit(false);
        Statement st = conn.createStatement();


        ResultSet rs = st.executeQuery("select age from users where id = 1");
        rs.next();
        int age = rs.getInt(1) ;
        //此处断点，可以读到A事务中的数据80，但是A事务还没有提交数据
        System.out.println(age);
        System.out.println("===============");
        conn.commit();
        conn.close();
    }
}
