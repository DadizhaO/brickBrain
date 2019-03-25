import config.DataConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.OfficeService;

import java.math.BigDecimal;

public class App {
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DataConfig.class);
        OfficeService officeService = context.getBean(OfficeService.class);

        LOG.info(officeService.findOfficeById(BigDecimal.valueOf(22)));

        LOG.info(officeService.getAllOffices());

        LOG.info(officeService.findByCityStartingWithIgnoreCase("бубу"));

    }
}
