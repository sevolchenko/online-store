package ru.vsu.cs.volchenko.site.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.volchenko.site.dto.OrderDTO;
import ru.vsu.cs.volchenko.site.entity.OrderDetails;
import ru.vsu.cs.volchenko.site.services.OrdersService;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@RestController
@RequestMapping("/cart")
public class RestCartController {

    private final OrdersService ordersService;

    @PostMapping(value = "/addCartInfo", consumes="application/json", produces="application/json")
    public String addCartInfo(@RequestBody OrderDTO orderDTO) {

        OrderDetails orderDetails = ordersService.save(orderDTO.getOrderDetails());

        Arrays.stream(orderDTO.getOrderContext())
                .filter(Objects::nonNull)
                .forEach(orderedProductDTO -> ordersService.addProduct(orderedProductDTO, orderDetails));

        return "{\"orderId\": \"" + orderDetails.getId() + "\"}";
    }

}
