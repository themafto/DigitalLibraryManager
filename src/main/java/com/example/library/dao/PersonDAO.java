package com.example.library.dao;


import com.example.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllPerson(){
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, new PersonMapper());
    }

    public Person getPersonById(long id){
        String sql = "SELECT * FROM person WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
    }
    public void savePerson(Person person){
        String sql = "INSERT INTO person(name, id, age, email) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, person.getName(), person.getId(), person.getAge(), person.getEmail());

    }
    public void update(long id, Person updatedPerson){
        String sql = "UPDATE person SET name=?, age=?, email=? WHERE id=?";
        jdbcTemplate.update(sql, updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);

    }

    public void deletePerson(long id){
        String sql = "DELETE FROM person WHERE id=?";
        jdbcTemplate.update(sql, id);

    }
}