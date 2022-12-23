package ru.vsu.cs.volchenko.site.entity;

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
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
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
