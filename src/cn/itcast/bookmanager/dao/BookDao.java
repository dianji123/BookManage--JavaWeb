package cn.itcast.bookmanager.dao;

import cn.itcast.bookmanager.model.Book;
import cn.itcast.bookmanager.model.BorrowDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BookDao {
    public ResultSet list(Connection conn, Book book) throws Exception{
        String sql = "select * from book join book_type on book.type_id = book_type.id";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return rs;
    }
}
