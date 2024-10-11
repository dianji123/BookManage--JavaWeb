package cn.itcast.bookmanager.dao;

import cn.itcast.bookmanager.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public int addUser(Connection conn, User user) throws Exception{
        //查询注册用户是否存在
        String sql = "select * from user where username=? ";
        PreparedStatement pstmt =
                (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1, user.getUserName());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return 2;
        }
        sql = "insert into user (username,password,role,sex,phone) values(?,?,?,?,?) ";
        PreparedStatement pstmt2 =
                (PreparedStatement)conn.prepareStatement(sql);
        pstmt2.setString(1, user.getUserName());
        pstmt2.setString(2, user.getPassword());
        pstmt2.setInt(3, user.getRole());
        pstmt2.setString(4, user.getSex());
        pstmt2.setString(5, user.getPhone());
        return pstmt2.executeUpdate();
    }

    public User login(Connection conn,User user) throws Exception{
        User resultUser = null;
        String sql = "select * from user where username=? and password=? and role=? ";
        PreparedStatement pstmt = (PreparedStatement)conn.prepareStatement(sql);
        pstmt.setString(1,user.getUserName());
        pstmt.setString(2,user.getPassword());
        pstmt.setInt(3,user.getRole());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            resultUser =new User();
            resultUser.setUserId(rs.getInt("id"));
            resultUser.setUserName(rs.getString("username"));
            resultUser.setSex(rs.getString("sex"));
            resultUser.setPhone(rs.getString("phone"));
        }
        return resultUser;
    }
}
