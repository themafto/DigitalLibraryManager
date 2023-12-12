package com.example.library.controllers;



import com.example.library.dao.PersonDAO;
import com.example.library.models.Person;
import com.example.library.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String showPeople(Model model){
        model.addAttribute("AllPeople", personDAO.getAllPerson());
        return "page-show-all-people";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "page-new-person";
    }
    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") long id, Model model){
        model.addAttribute("books", personDAO.getBooksByPersonId(id));
        model.addAttribute("person", personDAO.getPersonById(id));
        return "page-show-person";
    }

    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") long id, Model model){
        model.addAttribute("person", personDAO.getPersonById(id));
        return "page-edit-person";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "page-new-person";

        personDAO.savePerson(person);
        return "redirect:/people";
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "page-edit-person";
        {
            personDAO.update(id, person);
            return "redirect:/people";
        }
    }
    @DeleteMapping
    public String delete(long id){
        personDAO.deletePerson(id);
        return  "redirect:/people";
    }
}
