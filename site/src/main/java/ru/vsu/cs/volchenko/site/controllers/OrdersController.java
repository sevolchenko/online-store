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
import ru.vsu.cs.volchenko.site.entity.Product;
import ru.vsu.cs.volchenko.site.services.OrdersService;
import ru.vsu.cs.volchenko.site.services.ProductsService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private final ProductsService productsService;

    @GetMapping("/new")
    public String newGet(@ModelAttribute("orderDetails") OrderDetails orderDetails) {
        return "orders/new";
    }

    @PostMapping(value = "/new", params = {"cart"})
    public String createNew(@RequestParam(name = "cart") String cart,
                            @ModelAttribute("orderDetails") @Valid OrderDetails orderDetails,
                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "orders/new";
        }

        ordersService.save(orderDetails);

        List<Product> products = productsService.getCartProducts(cart);

        return ("redirect:/orders/approve");
    }

    @GetMapping("/approve")
    public String approve(Model model,
                          @ModelAttribute("orderDetails") OrderDetails orderDetails) {
        model.addAttribute("orderDetails", orderDetails);
        return "orders/approve";
    }

//    @PostMapping(value = "/addCartInfo", consumes="application/json")
//    public String addCartInfo(@RequestPart OrderedProductDTO[] dtoArr,
//                              @RequestPart OrderDetails orderDetails,
//                              RedirectAttributes redirectAttrs) {
//
//        Arrays.stream(dtoArr)
//                .filter(Objects::nonNull)
//                .forEach(ordersService::addProduct);
//
//        ordersService.save(orderDetails);
//
//        redirectAttrs.addFlashAttribute("orderDetails", orderDetails);
//
//        return ("redirect:/orders/success");
//    }

    @PostMapping(value = "/addCartInfo", consumes="application/json")
    public String addCartInfo(@RequestBody String json) {

        System.out.println(json);

        return ("redirect:/orders/success");
    }

    @GetMapping("/success")
    public String successGet(Model model,
                             @ModelAttribute("orderDetails") OrderDetails orderDetails) {

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
