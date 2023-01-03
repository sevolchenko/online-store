package ru.vsu.cs.volchenko.site.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vsu.cs.volchenko.site.dto.OrderedProductDTO;
import ru.vsu.cs.volchenko.site.entity.OrderDetails;
import ru.vsu.cs.volchenko.site.entity.Product;
import ru.vsu.cs.volchenko.site.services.OrdersService;
import ru.vsu.cs.volchenko.site.services.ProductsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductsService productsService;
    private final OrdersService ordersService;

    @GetMapping()
    public String index(@ModelAttribute("orderDetails") OrderDetails orderDetails) {
        return "cart/index";
    }
    @GetMapping(params = {"cart"})
    public String index(@RequestParam(name = "cart") String cart,
                        @ModelAttribute("orderDetails") OrderDetails orderDetails,
                        Model model) {
        model.addAttribute("products", productsService.getCartProducts(cart));
        return "cart/index";
    }

    @PostMapping(value = "/addCartInfo", consumes="application/json")
    public String addCartInfo(@RequestPart OrderedProductDTO[] dtoArr,
                              @RequestPart OrderDetails orderDetails,
                              RedirectAttributes redirectAttrs) {

        Arrays.stream(dtoArr)
                .filter(Objects::nonNull)
                .forEach(ordersService::addProduct);

        ordersService.save(orderDetails);

        redirectAttrs.addFlashAttribute("orderDetails", orderDetails);

        return ("redirect:/orders/success");
    }


}
