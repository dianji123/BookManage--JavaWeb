package cn.itcast.bookmanager.dao;

import cn.itcast.bookmanager.model.BorrowDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BorrowDetailDao {
    public int add(Connection conn, BorrowDetail borrowDetail) throws Exception{
        String sql = "insert into borrowdetail(user_id,book_id,status,borrow_time)" +
                "values(?,?,?,?)";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setInt(1, borrowDetail.getUserId());
        pstmt.setInt(2, borrowDetail.getBookId());
        pstmt.setInt(3, borrowDetail.getStatus());
        pstmt.setString(4, borrowDetail.getBorrowTime());
        return pstmt.executeUpdate();
    }

    public int returnBook(Connection conn, BorrowDetail borrowDetail) throws Exception{
        String sql = "update borrowdetail set status=?,return_time=? where id=?";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setInt(1, borrowDetail.getStatus());
        pstmt.setString(2, borrowDetail.getReturnTime());
        pstmt.setInt(3, borrowDetail.getBorrowId());
        return pstmt.executeUpdate();
    }

    public ResultSet list(Connection conn, BorrowDetail borrowDetail) throws Exception{
        String sql = "select * from borrowdetail join book on borrowdetail.book_id = book.id where user_id = ? and borrowdetail.book_id = ? and borrowdetail.status = 1";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setInt(1, borrowDetail.getUserId());
        pstmt.setInt(2,borrowDetail.getBookId());
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }

    public ResultSet list1(Connection conn, BorrowDetail borrowDetail) throws Exception{
        String sql = "select borrowdetail.*,book.book_name from borrowdetail join book on borrowdetail.book_id = book.id where user_id = ? ";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setInt(1, borrowDetail.getUserId());
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }

}
