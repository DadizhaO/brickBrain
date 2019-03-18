package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("repository")
@PropertySource("classpath:database.properties")
@ComponentScan("service")

public class DataConfig {

    private static final Logger LOG = LogManager.getLogger(DataConfig.class);

    @Value("${driver}")
    private String propertyDriver;
    @Value("${url}")
    private String propertyUrl;
    @Value("${user}")
    private String propertyUsername;
    @Value("${password}")
    private String propertyPassword;
    @Value("${maxactive}")
    private String propertyMaxActive;
    @Value("${initialsize}")
    private String propertyInitialSize;

    @Value("${hibernate.show_sql}")
    private String propertyShowSql;
    @Value("${hibernate.dialect}")
    private String propertyDialect;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
        lfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        lfb.setDataSource(dataSource());
        lfb.setPackagesToScan("model");
        lfb.setJpaProperties(hibernateProps());
        return lfb;
    }


    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        try {
            cpds.setDriverClass(propertyDriver);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(propertyUrl);

        // Optional Settings
        cpds.setInitialPoolSize(Integer.parseInt(propertyInitialSize));
        cpds.setMaxPoolSize(Integer.parseInt(propertyMaxActive));

        LOG.debug("Establish connection to DB={}", propertyUrl);
        return cpds;

    }

    private Properties hibernateProps() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", propertyDialect);
        properties.setProperty("hibernate.show_sql", propertyShowSql);
        properties.setProperty("hibernate.format_sql", "true");
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}
