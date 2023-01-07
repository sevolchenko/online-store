package ru.vsu.cs.volchenko.site.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.volchenko.site.entity.OrderDetails;
import ru.vsu.cs.volchenko.site.services.OrdersService;


@AllArgsConstructor(onConstructor=@__(@Autowired))
@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("orderDetails", ordersService.findOne(id));
        model.addAttribute("productsNames", ordersService.getOrderProductsNames(id));
        return "orders/show";
    }

//    @PostMapping("/new")
//    public String create(@ModelAttribute("product") @Valid OrderDetails orderDetails,
//                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "cart/index";
//
//        ordersService.save(orderDetails);
//        return "redirect:/products/" + product.getId();
//    }


}
