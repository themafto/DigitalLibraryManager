package com.example.library.dao;


import com.example.library.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class BookDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBook(){
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BookMapper());
    }
    public Book getBookById(long id){
        String sql = "SELECT * FROM book WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BookMapper());
    }
    public void saveBook(Book book) {
        String sql = "INSERT INTO book(name, author, year, id) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getYear(), book.getId());
    }

    public void update(long id, Book updatedBook) {
        String sql = "UPDATE book SET name=?, author=?, age=? WHERE id=?";
        jdbcTemplate.update(sql, id, updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), updatedBook.getId());
    }
    public void delete(long id) {
        String sql = "DELETE FROM book WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}
