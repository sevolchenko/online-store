package ru.vsu.cs.volchenko.site.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.cs.volchenko.site.services.ProductsService;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductsService productsService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", productsService.findAll());
        return "cart/index";
    }

}
