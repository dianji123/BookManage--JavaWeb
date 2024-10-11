package cn.itcast.bookmanager.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
    private String dbDriver = "com.mysql.jdbc.Driver";
    private String dbUrl =
            "jdbc:mysql://localhost:3306/booksystem?characterEncoding=utf-8";
    private String dbUsername = "root";
    private String dbPassword = "123456";
    public Connection getConnection() throws Exception{
        Class.forName(dbDriver);
        Connection conn = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        return conn;
    }
    public void closeConn(Connection conn) throws Exception {
        if(conn != null){
            conn.close();
        }
    }
}
