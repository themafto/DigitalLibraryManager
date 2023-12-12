package com.example.library.controllers;


import com.example.library.dao.BookDAO;
import com.example.library.dao.PersonDAO;
import com.example.library.models.Book;
import com.example.library.models.Person;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookDAO bookDAO;
    private PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String allBook(Model model){
        model.addAttribute("books", bookDAO.getAllBook());
        return "page-show-all-book";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.getBookById(id));

        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", personDAO.getAllPerson());
        return "page-show-book";
    }

    @GetMapping("/new")
    public String createBook(@ModelAttribute("book") Book book){
        return "page-new-book";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("book") Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "page-new-book";
        bookDAO.saveBook(book);
        return "redirect:/book";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id")long id, Model model){
        model.addAttribute("book", bookDAO.getBookById(id));
        return "page-edit-book";
    }

    @PatchMapping("/edit/{id}")
    public String update(@PathVariable("id") long id,@ModelAttribute("book") Book updatedBook,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "page-edit-book";
        bookDAO.update(id, updatedBook);
        return "redirect:/book";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        bookDAO.delete(id);
        return "redirect:/book";
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") long id) {
        bookDAO.release(id);
        return "redirect:/book/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") long id, @ModelAttribute("person") Person selectedPerson) {
        bookDAO.assign(id, selectedPerson);
        return "redirect:/book/" + id;
    }
}
