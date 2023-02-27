package ru.vsu.cs.volchenko.site.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.volchenko.site.entity.OrderDetails;
import ru.vsu.cs.volchenko.site.entity.OrderedProduct;

import java.util.List;

@Repository
public interface OrderedProductsRepository extends JpaRepository<OrderedProduct, Integer> {

    List<OrderedProduct> findAllByOrOrderDetailsEquals(OrderDetails orderDetails);

}
