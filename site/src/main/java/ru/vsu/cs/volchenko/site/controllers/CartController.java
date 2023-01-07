package ru.vsu.cs.volchenko.site.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vsu.cs.volchenko.site.entity.OrderDetails;
import ru.vsu.cs.volchenko.site.services.OrdersService;
import ru.vsu.cs.volchenko.site.services.ProductsService;


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

    @GetMapping("/success")
    public String index(@ModelAttribute("orderDetails") OrderDetails orderDetails,
                        Model model) {

        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("overallPrice", ordersService.getOverallPrice(orderDetails));

        return "cart/success";
    }


}
