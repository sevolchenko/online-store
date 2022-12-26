package ru.vsu.cs.volchenko.site.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.vsu.cs.volchenko.site.entity.Photo;
import ru.vsu.cs.volchenko.site.entity.Product;
import ru.vsu.cs.volchenko.site.services.CategoriesService;
import ru.vsu.cs.volchenko.site.services.ProductsService;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;
    private final CategoriesService categoriesService;


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", productsService.findAll());
        return "products/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productsService.findOne(id));
        return "products/show";
    }

    @GetMapping("/new")
    public String newGet(Model model,
                           @ModelAttribute("product") Product product) {
        model.addAttribute("categories", categoriesService.findAll());
        return "products/new";
    }


    @PostMapping(path = "/new", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String create(@RequestPart("image") MultipartFile multipartFile,
                         @ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "products/new";

        try {
            byte[] picBytes = multipartFile.getBytes();
            Blob blob = new SerialBlob(picBytes);
            Photo photo = new Photo();
            photo.setPhoto(blob);
            product.setPhotos(new ArrayList<>());
            product.getPhotos().add(photo);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        productsService.save(product);
        return "redirect:/products/" + product.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("categories", categoriesService.findAll());
        model.addAttribute("product", productsService.findOne(id));
        return "products/edit";
    }

    @PatchMapping(path = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String update(@RequestPart("image") MultipartFile multipartFile,
                         @ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "products/edit";

        try {
            byte[] picBytes = multipartFile.getBytes();
            Blob blob = new SerialBlob(picBytes);
            Photo photo = new Photo();
            photo.setPhoto(blob);
            product.setPhotos(new ArrayList<>());
            product.getPhotos().add(photo);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        productsService.update(id, product);
        return "redirect:/products/" + product.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productsService.delete(id);
        return "redirect:/products";
    }
}
