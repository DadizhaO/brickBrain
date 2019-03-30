package dto;

import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import util.DtoModelsUtil;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class OfficeRequestTest {

    private Validator validator;
    private Errors errors;
    private OfficeRequest officeRequest;

    @Before
    public void setUp() {
        LocalValidatorFactoryBean localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();
        validator = localValidatorFactory;
        officeRequest = DtoModelsUtil.officeRequest();
        errors = new BeanPropertyBindingResult(officeRequest, "officeRequest");
    }

    @Test
    public void testOfficeRequestValid() {
        validator.validate(officeRequest, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testOfficeRequestOfficeIdIsNotCorrect() {
        officeRequest.setOfficeId(BigDecimal.valueOf(-11));
        validator.validate(officeRequest, errors);
        assertEquals(errors.getFieldError("officeId").getDefaultMessage(), "1");
    }

    @Test
    public void testOfficeRequestCityIsNull() {
        officeRequest.setCity(null);
        validator.validate(officeRequest, errors);
        assertEquals(errors.getFieldError("city").getDefaultMessage(), "2");
    }

    @Test
    public void testOfficeRequestCityIsNotCorrect() {
        officeRequest.setCity("@#$%^");
        validator.validate(officeRequest, errors);
        assertEquals(errors.getFieldError("city").getDefaultMessage(), "2");
    }

    @Test
    public void testOfficeRequestSalesIsNull() {
        officeRequest.setSales(null);
        validator.validate(officeRequest, errors);
        assertEquals(errors.getFieldError("sales").getDefaultMessage(), "3");
    }

    @Test
    public void testOfficeRequestSaleIsNotCorrect() {
        officeRequest.setSales(BigDecimal.valueOf(-111));
        validator.validate(officeRequest, errors);
        assertEquals(errors.getFieldError("sales").getDefaultMessage(), "3");
    }
}
