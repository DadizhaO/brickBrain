package repository;


import config.DataConfig;
import model.Office;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.OfficeService;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@TestPropertySource("classpath:test.properties")
public class OfficeRepositoryIntegrationH2Test {

    private static final Office NOT_EXIST_OFFICE_INSERT = new Office(BigDecimal.valueOf(333333));
    private static final Office ALREADY_EXIST_OFFICE_UPDATE = new Office(BigDecimal.valueOf(111111));
    private static final Office ALREADY_EXIST_OFFICE_DELETE = new Office(BigDecimal.valueOf(222222));
    private static final BigDecimal NOT_EXIST_OFFICE_ID = BigDecimal.valueOf(-1);

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private DataSource dataSource;

    @Before
    public void initDb() {
        Resource initSchema = new ClassPathResource("script\\schema.sql");
        Resource data = new ClassPathResource("script\\data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    }

    @Test
    public void testGetAllOffices() {
        List<Office> offices = officeRepository.findAll();
        System.out.println(offices);
        assertTrue(offices.size() >= 2);
    }

    @Test
    public void testGetAllOfficesService() {
        Set<Office> offices = officeService.getAllOffices();
        System.out.println(offices);
        assertTrue(offices.size() >= 2);
    }

    @Test
    public void testInsertOffice() {
        officeRepository.save(NOT_EXIST_OFFICE_INSERT);
    }

    @Test
    public void testInsertOfficeService() {
        officeService.insertOffice(NOT_EXIST_OFFICE_INSERT);
    }

    @Test
    public void testUpdateOffice() {
        ALREADY_EXIST_OFFICE_UPDATE.setSales(BigDecimal.valueOf(-333));
        ALREADY_EXIST_OFFICE_UPDATE.setTarget(BigDecimal.valueOf(-111));
        officeRepository.save(ALREADY_EXIST_OFFICE_UPDATE);
        System.out.println(ALREADY_EXIST_OFFICE_UPDATE);
    }

    @Test
    public void testDeleteOffice() {
        officeRepository.delete(ALREADY_EXIST_OFFICE_DELETE);
    }

    @Test
    public void testFindOfficeByIdNotPresent() {
        Optional<Office> office = officeRepository.findById(NOT_EXIST_OFFICE_ID);
        assertFalse(office.isPresent());
    }

    @Test
    public void testFindOfficeById() {
        Optional<Office> office = officeRepository.findById(BigDecimal.valueOf(1111));
        assertTrue(office.isPresent());
    }

    @Test
    public void testFindByCityIgnoreCase() {
        Set<Office> offices = officeRepository.findByCityStartingWithIgnoreCase("ky");
        assertEquals(1, offices.size());
    }

}
