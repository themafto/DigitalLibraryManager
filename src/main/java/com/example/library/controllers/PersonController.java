package com.example.library.controllers;



import com.example.library.dao.PersonDAO;
import com.example.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
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
        model.addAttribute("person", personDAO.getPersonById(id));
        return "page-show-person";
    }

    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") long id, Model model){
        model.addAttribute("person", personDAO.getPersonById(id));
        return "page-edit-person";
    }


    @PostMapping
    public String create(@ModelAttribute("person") @Validated Person person,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "page-new-person";
        personDAO.savePerson(person);
        return "redirect:/people";
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("person") @Validated Person person,
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
