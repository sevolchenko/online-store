package ru.vsu.cs.volchenko.site.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.volchenko.site.dao.CategoryDAO;
import ru.vsu.cs.volchenko.site.dao.PhotoDAO;
import ru.vsu.cs.volchenko.site.dao.ProductDAO;
import ru.vsu.cs.volchenko.site.models.Product;

import javax.validation.Valid;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;
    private final PhotoDAO photoDAO;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", productDAO.get());
        model.addAttribute("photos", photoDAO.get());
        return "product/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productDAO.get(id));
        model.addAttribute("photos", photoDAO.get(id));
        model.addAttribute("category", categoryDAO.get(productDAO.get(id).getCategoryId()));
        return "product/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("product") Product product) {
        return "product/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "product/new";

        productDAO.save(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", productDAO.get(id));
        return "product/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "product/edit";

        productDAO.update(id, product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productDAO.delete(id);
        return "redirect:/product";
    }
}
