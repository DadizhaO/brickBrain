package service;

import model.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface OrderService {
    Set<Order> getAllOrders();

    Set<Order> findByQtyBetween(int minQty, int maxQty);

    List<Order> findOrdersByAmount(BigDecimal amount);

    Optional<Order> findOrderById(BigDecimal id);

    void insertOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(BigDecimal id);
}
