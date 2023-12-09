package com.example.library.dao;


import com.example.library.models.Book;
import com.example.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBook(){
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }
    public Book getBookById(long id){
        String sql = "SELECT * FROM book WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
    public void saveBook(Book book) {
        String sql = "INSERT INTO book(name, author, year) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(long id, Book updatedBook) {
        String sql = "UPDATE book SET name=?, author=?, age=? WHERE id=?";
        jdbcTemplate.update(sql, id, updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear());
    }
    public void delete(long id) {
        String sql = "DELETE FROM book WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<Person> getBookOwner(long id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
                        "WHERE Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
    public void release(long id) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id);
    }

    public void assign(long id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", selectedPerson.getId(), id);
    }
}
