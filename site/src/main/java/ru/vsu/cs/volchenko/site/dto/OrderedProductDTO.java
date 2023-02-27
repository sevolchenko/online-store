package ru.vsu.cs.volchenko.site.dto;

import lombok.*;
import ru.vsu.cs.volchenko.site.entity.ProductSize;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProductDTO {

    private Integer id;

    private Integer count;

    private ProductSize size;

}
