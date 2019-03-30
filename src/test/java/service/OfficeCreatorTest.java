package service;

import dto.OfficeRequest;
import model.Office;
import org.junit.Test;
import service.impl.OfficeCreatorImpl;
import util.DtoModelsUtil;

import static org.junit.Assert.assertEquals;

public class OfficeCreatorTest {

    OfficeCreator creator = new OfficeCreatorImpl();

    @Test
    public void testOfficeCreate(){
        Office expected = DtoModelsUtil.office();
        OfficeRequest officeRequest = DtoModelsUtil.officeRequest();
        Office actual = creator.createOffice(officeRequest);
        assertEquals(expected.toString(),actual.toString());
    }
}
