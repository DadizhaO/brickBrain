package util;

import dto.OfficeDetails;
import dto.OfficeRequest;
import model.Office;

import java.math.BigDecimal;

public class DtoModelsUtil {
    private static final BigDecimal ID = BigDecimal.valueOf(100);
    private static final String CITY = "Lviv";
    private static final String REGION = "West";
    private static final BigDecimal TARGET = BigDecimal.valueOf(700);
    private static final BigDecimal SALES = BigDecimal.valueOf(800);

    public static Office office() {
        Office office = new Office();
        office.setOfficeId(ID);
        office.setCity(CITY);
        office.setRegion(REGION);
        office.setTarget(TARGET);
        office.setSales(SALES);
        return office;
    }

    public static OfficeRequest officeRequest() {
        OfficeRequest officeRequest = new OfficeRequest();
        officeRequest.setOfficeId(ID);
        officeRequest.setCity(CITY);
        officeRequest.setSales(SALES);
        officeRequest.setOfficeDetails(officeDetails());
        return officeRequest;
    }

    public static OfficeDetails officeDetails() {
        OfficeDetails officeDetails = new OfficeDetails();
        officeDetails.setTarget(TARGET);
        officeDetails.setRegion(REGION);
        return officeDetails;
    }

}
