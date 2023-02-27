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
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;
    private final CategoriesService categoriesService;


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", productsService.findAllNotHidden());
        return "products/index";
    }

    @GetMapping(params = {"page", "itemsPerPage"})
    public String indexWithPages(@RequestParam("page") int page,
                                 @RequestParam("itemsPerPage") int itemsPerPage,
                                 Model model) {
        model.addAttribute("products", productsService.findAllNotHidden(page, itemsPerPage));
        model.addAttribute("overallProducts", productsService.countOfNotHidden());
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
    public String create(@RequestPart("images[]") MultipartFile[] multipartFiles,
                         @ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoriesService.findAll());
            return "products/new";
        }

        List<Photo> photos = new ArrayList<>();

        Arrays.stream(multipartFiles).forEach(multipartFile -> {
            try {
                byte[] picBytes = multipartFile.getBytes();
                Blob blob = new SerialBlob(picBytes);
                Photo photo = new Photo();
                photo.setPhoto(blob);
                photos.add(photo);
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        });
        product.setPhotos(photos);

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
    public String update(@RequestPart("images[]") MultipartFile[] multipartFiles,
                         @ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult,
                         @PathVariable("id") int id,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoriesService.findAll());
            return "products/edit";
        }

        List<Photo> photos = new ArrayList<>();

        Arrays.stream(multipartFiles).forEach(multipartFile -> {
            try {
                byte[] picBytes = multipartFile.getBytes();
                Blob blob = new SerialBlob(picBytes);
                Photo photo = new Photo();
                photo.setPhoto(blob);
                photos.add(photo);
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        });
        product.setPhotos(photos);

        productsService.update(id, product);
        return "redirect:/products/" + product.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productsService.delete(id);
        return "redirect:/products";
    }
}
