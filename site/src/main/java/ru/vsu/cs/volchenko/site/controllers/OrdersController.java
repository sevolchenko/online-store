package ru.vsu.cs.volchenko.site.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.cs.volchenko.site.entity.OrderDetails;
import ru.vsu.cs.volchenko.site.entity.OrderedProduct;
import ru.vsu.cs.volchenko.site.services.OrdersService;


@AllArgsConstructor(onConstructor=@__(@Autowired))
@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        OrderDetails orderDetails = ordersService.findOne(id);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("orderedProducts", orderDetails.getOrderedProducts().stream()
                .map(OrderedProduct::getProduct)
                .toList());
        model.addAttribute("overallPrice", ordersService.getOverallPrice(orderDetails));
        return "orders/show";
    }


}
