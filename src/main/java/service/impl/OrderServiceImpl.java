package service.impl;

import exception.DeleteException;
import model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import repository.OrderRepository;
import service.OrderService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOG = LogManager.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Set<Order> getAllOrders() {
        return new HashSet<>(orderRepository.findAll());
    }

    @Override
    public Set<Order> findByQtyBetween(int minQty, int maxQty) {
        return new HashSet<>(orderRepository.findByQtyBetween(BigDecimal.valueOf(minQty), BigDecimal.valueOf(maxQty)));
    }

    @Override
    public List<Order> findOrdersByAmount(BigDecimal amount) {
        return orderRepository.findOrdersByAmount(amount);
    }

    @Override
    public Optional<Order> findOrderById(BigDecimal id) {

        return orderRepository.findById(id);
    }

    @Override
    public void insertOrder(Order order) {
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.saveAndFlush(order);

    }

    @Override
    public void deleteOrder(BigDecimal id) {
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOG.warn("Cannot deleteOrder with id{}, because it don't present", id);
            throw new DeleteException("Cannot delete Order by Id=" + id + ", because it don't present");
        }
    }
}
