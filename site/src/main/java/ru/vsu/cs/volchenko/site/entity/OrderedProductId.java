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

    private OrderDetails orderDetails;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderedProductId that = (OrderedProductId) o;

        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        return orderDetails != null ? orderDetails.equals(that.orderDetails) : that.orderDetails == null;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (orderDetails != null ? orderDetails.hashCode() : 0);
        return result;
    }
}
