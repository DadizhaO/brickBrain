

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

public class OfficeDetailsTest {

    private Validator validator;
    private OfficeDetails officeDetails;
    private Errors errors;

    @Before
    public void setUp() {
        LocalValidatorFactoryBean localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();
        validator = localValidatorFactory;
        officeDetails = DtoModelsUtil.officeDetails();
        errors = new BeanPropertyBindingResult(officeDetails, "officeDetails");
    }

    @Test
    public void testOfficeDetailsTargetIsNull() {
        officeDetails.setTarget(null);
        validator.validate(officeDetails, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testOfficeDetailsTargetIsNotCorrect() {
        officeDetails.setTarget(BigDecimal.valueOf(20));
        validator.validate(officeDetails, errors);
        assertEquals(errors.getFieldError("target").getDefaultMessage(), "5");
    }

    @Test
    public void testOfficeDetailsRegionIsNull() {
        officeDetails.setRegion(null);
        validator.validate(officeDetails, errors);
        assertEquals(errors.getFieldError("region").getDefaultMessage(), "4");
    }

    @Test
    public void testOfficeDetailsRegionIsNotCorrect() {
        officeDetails.setRegion("00sdfe");
        validator.validate(officeDetails, errors);
        assertEquals(errors.getFieldError("region").getDefaultMessage(), "4");
    }

    @Test
    public void testOfficeDetailsValid() {
        validator.validate(officeDetails, errors);
        assertFalse(errors.hasErrors());
    }
}

