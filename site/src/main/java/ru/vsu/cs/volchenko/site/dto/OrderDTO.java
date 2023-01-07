package ru.vsu.cs.volchenko.site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private OrderedProductDTO[] orderContext;

    private OrderDetailsDTO orderDetails;

}
