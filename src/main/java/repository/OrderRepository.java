package repository;

import model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, BigDecimal> {

    List<Order> findOrdersByAmount(BigDecimal amount);

    Set<Order> findByQtyBetween(BigDecimal minQty, BigDecimal maxQty);


}
