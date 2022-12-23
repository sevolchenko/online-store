package ru.vsu.cs.volchenko.site.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.volchenko.site.entity.Order;
import ru.vsu.cs.volchenko.site.repositories.OrdersRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class OrdersService {

    private OrdersRepository ordersRepository;

    public List<Order> findAll() {
        return ordersRepository.findAll();
    }

    public Order findOne(int id) {
        return ordersRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Order order) {
        ordersRepository.save(order);
    }

    @Transactional
    public void update(int id, Order updatedOrder) {
        updatedOrder.setId(id);
        ordersRepository.save(updatedOrder);
    }

    @Transactional
    public void delete(int id) {
        ordersRepository.deleteById(id);
    }

}
