package ru.vsu.cs.volchenko.site.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.volchenko.site.entity.OrderDetails;

@Repository
public interface OrdersRepository extends JpaRepository<OrderDetails, Integer> {

    OrderDetails findTopByOrderByIdDesc();

}
