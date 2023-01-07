package ru.vsu.cs.volchenko.site.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.volchenko.site.dto.OrderDetailsDTO;
import ru.vsu.cs.volchenko.site.dto.OrderedProductDTO;
import ru.vsu.cs.volchenko.site.entity.OrderDetails;
import ru.vsu.cs.volchenko.site.entity.OrderState;
import ru.vsu.cs.volchenko.site.entity.OrderedProduct;
import ru.vsu.cs.volchenko.site.entity.Product;
import ru.vsu.cs.volchenko.site.repositories.OrderedProductsRepository;
import ru.vsu.cs.volchenko.site.repositories.OrdersRepository;
import ru.vsu.cs.volchenko.site.repositories.ProductsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class OrdersService {

    private OrdersRepository ordersRepository;
    private OrderedProductsRepository orderedProductsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<OrderDetails> findAll() {
        return ordersRepository.findAll();
    }

    public OrderDetails findOne(int id) {
        return ordersRepository.findById(id).orElse(null);
    }

    public int getOverallPrice(OrderDetails orderDetails) {
        return orderedProductsRepository.findAllByOrOrderDetailsEquals(orderDetails).stream()
                .mapToInt((orderedProduct) -> orderedProduct.getCount() * orderedProduct.getPricePerOne())
                .sum() + orderDetails.getShipPrice();
    }

    @Transactional
    public OrderDetails save(OrderDetailsDTO orderDTO) {

        OrderDetails order = new OrderDetails();

        order.setName(orderDTO.getName());
        order.setPhoneNumber(orderDTO.getPhoneNumber());
        order.setEmail(orderDTO.getEmail());
        order.setPostIndex(orderDTO.getPostIndex());
        order.setCountry(orderDTO.getCountry());
        order.setCity(orderDTO.getCity());
        order.setAddress(orderDTO.getAddress());
        order.setComment(orderDTO.getComment());
        order.setPromoCode(orderDTO.getPromoCode());

        order.setOrderState(OrderState.CREATED);

        if (order.getCountry().equals("Russia") || order.getCountry().equals("Россия")) {
            order.setShipPrice(300);
        } else {
            order.setShipPrice(2000);
        }
        ordersRepository.save(order);

        return order;
    }

    @Transactional
    public void update(int id, OrderDetails updatedOrder) {
        updatedOrder.setId(id);
        ordersRepository.save(updatedOrder);
    }

    @Transactional
    public void delete(int id) {
        ordersRepository.deleteById(id);
    }

    @Transactional
    public void addProduct(OrderedProductDTO orderedProductDTO, OrderDetails orderDetails) {
        OrderedProduct orderedProduct = new OrderedProduct();
        orderedProduct.setOrderDetails(ordersRepository.findTopByOrderByIdDesc());
        Product product = entityManager.getReference(Product.class, orderedProductDTO.getId());
        orderedProduct.setProduct(product);
        orderedProduct.setOrderDetails(orderDetails);
        orderedProduct.setProductSize(orderedProductDTO.getSize());
        orderedProduct.setCount(orderedProductDTO.getCount());
        orderedProduct.setPricePerOne(product.getCurrentPrice());
        orderedProductsRepository.save(orderedProduct);
    }

    public OrderDetails getLast() {
        return ordersRepository.findTopByOrderByIdDesc();
    }

    public List<String> getOrderProductsNames(int id) {
        return ordersRepository.findById(id).orElseGet(null).getOrderedProducts().stream().map(orderedProduct -> orderedProduct.getProduct().getName()).toList();
    }

}
