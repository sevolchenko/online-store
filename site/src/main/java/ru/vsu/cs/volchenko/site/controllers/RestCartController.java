package ru.vsu.cs.volchenko.site.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.volchenko.site.dto.OrderDTO;
import ru.vsu.cs.volchenko.site.dto.OrderedProductDTO;
import ru.vsu.cs.volchenko.site.entity.OrderDetails;
import ru.vsu.cs.volchenko.site.services.OrdersService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@RestController
@RequestMapping("/cart")
public class RestCartController {

    private final OrdersService ordersService;

    @PostMapping(value = "/addCartInfo", consumes="application/json", produces="application/json")
    public String addCartInfo(@Valid @RequestBody OrderDTO orderDTO,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder("{\"errors\": {");

            List<FieldError> allErrors = bindingResult.getFieldErrors();
            for (int idx = 0; idx < allErrors.size(); idx++) {
                if (idx > 0) {
                    sb.append(",\n");
                }

                FieldError fieldError = allErrors.get(idx);
                String fullFieldName = fieldError.getField();
                String key = fullFieldName.substring(fullFieldName.indexOf(".") + 1);
                String value = fieldError.getDefaultMessage();
                sb.append("\"").append(key).append("\": \"").append(value).append("\"");
            }

            sb.append("}}");

            return sb.toString();
        }

        OrderDetails orderDetails = ordersService.save(orderDTO.getOrderDetails());

        Arrays.stream(orderDTO.getOrderContext())
                .filter(Objects::nonNull)
                .forEach(orderedProductDTO -> ordersService.addProduct(orderedProductDTO, orderDetails));

        return "{\"orderId\": \"" + orderDetails.getId() + "\"}";
    }
}
