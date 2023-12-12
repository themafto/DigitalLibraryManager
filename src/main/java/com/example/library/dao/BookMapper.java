package com.example.library.dao;

import com.example.library.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book= new Book();

        book.setName(rs.getString("name"));
        book.setYear(rs.getInt("age"));
        book.setAuthor(rs.getString("email"));
        book.setId(rs.getLong("id"));
        return book;
    }
}
