package ru.vsu.cs.volchenko.site.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProductId implements Serializable {

    private Product product;

    private Order order;

    private ProductSize productSize;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderedProductId that = (OrderedProductId) o;

        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        return productSize == that.productSize;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (productSize != null ? productSize.hashCode() : 0);
        return result;
    }
}
