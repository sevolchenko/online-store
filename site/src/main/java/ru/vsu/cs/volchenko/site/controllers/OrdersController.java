package ru.vsu.cs.volchenko.site.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vsu.cs.volchenko.site.dto.OrderedProductDTO;
import ru.vsu.cs.volchenko.site.entity.OrderDetails;
import ru.vsu.cs.volchenko.site.services.OrdersService;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping("/new")
    public String newGet(@ModelAttribute("orderDetails") OrderDetails orderDetails) {
        return "orders/new";
    }

    @PostMapping("/new")
    public String createNew(@ModelAttribute("orderDetails") @Valid OrderDetails orderDetails,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            return "orders/new";
        }

        redirectAttrs.addFlashAttribute("orderDetails", orderDetails);

        return ("redirect:/orders/approve");
    }

    @GetMapping("/approve")
    public String approve(@ModelAttribute("orderDetails") OrderDetails orderDetails) {
        return "orders/approve";
    }

    @PostMapping("/approve")
    public void approvePost(@ModelAttribute("orderDetails") OrderDetails orderDetails) {

        ordersService.save(orderDetails);

    }

    @PostMapping(value = "/addCartInfo", consumes="application/json")
    public String addCartInfo(@RequestBody OrderedProductDTO[] dtoArr) {

        Arrays.stream(dtoArr)
                .filter(Objects::nonNull)
                .forEach(ordersService::addProduct);

        return ("redirect:/orders/success");
    }

    @GetMapping("/success")
    public String successGet(Model model) {

        OrderDetails orderDetails = ordersService.getLast();
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("overallPrice", ordersService.getOverallPrice(orderDetails));

        return "orders/success";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("orderDetails", ordersService.findOne(id));
        model.addAttribute("productsNames", ordersService.getOrderProductsNames(id));
        return "orders/show";
    }

}
