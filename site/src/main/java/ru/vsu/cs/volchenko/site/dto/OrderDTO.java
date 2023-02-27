package ru.vsu.cs.volchenko.site.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @NotEmpty(message = "There are no ordered products")
    private OrderedProductDTO[] orderContext;

    @NotNull(message = "Order details should not be null")
    @Valid
    private OrderDetailsDTO orderDetails;

}
