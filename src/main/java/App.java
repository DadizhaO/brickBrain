import model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.OfficeService;
import service.OrderService;

import java.math.BigDecimal;

public class App {
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("config");
        OrderService orderService = context.getBean(OrderService.class);
        OfficeService officeService = context.getBean(OfficeService.class);


        LOG.info(orderService.findOrdersByAmount(new BigDecimal(31.5)));

        LOG.info(orderService.getAllOrders());

        Order order = new Order(
                new BigDecimal(1111), null, null, null, null, null);

        orderService.insertOrder(order);
        orderService.deleteOrder(BigDecimal.valueOf(1111));
        orderService.deleteOrder(BigDecimal.valueOf(122222));
        LOG.info(officeService.findOfficeById(BigDecimal.valueOf(22)));

        LOG.info(orderService.findOrderById(BigDecimal.valueOf(112961)));
        LOG.info(officeService.getAllOffices());

        LOG.info(officeService.findByCityIgnoreCase("бубурино"));

    }
}
