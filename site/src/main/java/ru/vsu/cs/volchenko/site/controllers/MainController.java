package ru.vsu.cs.volchenko.site.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@Controller
@RequestMapping()
public class MainController {

    @GetMapping()
    public String index() {
        return "index";
    }

}
