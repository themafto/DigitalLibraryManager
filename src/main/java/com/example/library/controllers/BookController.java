package com.example.library.controllers;


import com.example.library.dao.BookDAO;
import com.example.library.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String allBook(Model model){
        model.addAttribute("books", bookDAO.getAllBook());
        return "page-show-all-book";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") long id, Model model){
        model.addAttribute("book", bookDAO.getBookById(id));
        return "page-show-book";

    }
    @GetMapping("/new")
    public String createBook(@ModelAttribute("book") Book book){
        return "page-new-book";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id")long id, Model model){
        model.addAttribute("book", bookDAO.getBookById(id));
        return "page-edit-book";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("book") Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "page-new-book";
        bookDAO.saveBook(book);
        return "redirect:/book";
    }

    @PatchMapping("/edit/{id}")
    public String update(@PathVariable("id") long id, Book updatedBook){
        bookDAO.update(id, updatedBook);
        return "redirect:/book";
    }
    @DeleteMapping("/delete")
    public String delete(){
        return "redirect:/book";
    }
}
