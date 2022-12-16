package ru.vsu.cs.volchenko.site.controllers;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.volchenko.site.dao.PersonDAO;
import ru.vsu.cs.volchenko.site.models.Person;
import ru.vsu.cs.volchenko.site.repository.PersonRepository;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/people")
@AllArgsConstructor
public class PeopleController {

    @Autowired
    private final PersonRepository personRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personRepository.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personRepository.getReferenceById(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        personRepository.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personRepository.getReferenceById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personRepository.save(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personRepository.deleteById(id);
        return "redirect:/people";
    }
}
