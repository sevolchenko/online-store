package ru.vsu.cs.volchenko.site.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(OrderedProductId.class)
@Table(name = "ordered_product")
public class OrderedProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private OrderDetails orderDetails;

    @Column(name = "size", columnDefinition = "ENUM('ONE_SIZE', 'XS', 'S', 'M', 'L', 'XL', 'XXL')")
    @Enumerated(EnumType.STRING)
    private ProductSize productSize;

    @Min(value = 1)
    @Column(name = "count")
    private int count;

    @Min(value = 0)
    @Column(name = "price_per_one")
    private int pricePerOne;

}
