package service.impl;

import dto.OfficeDetails;
import dto.OfficeRequest;
import model.Office;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import service.OfficeCreator;

@Service
public class OfficeCreatorImpl implements OfficeCreator {

    private static final Logger LOG = LogManager.getLogger(OfficeCreatorImpl.class);

    @Override
    public Office createOffice(OfficeRequest officeRequest) {
        LOG.info("createOffice, officeRequest={}", officeRequest);
        Office office = new Office();
        office.setOfficeId(officeRequest.getOfficeId());
        office.setSales(officeRequest.getSales());
        office.setCity(officeRequest.getCity());

        OfficeDetails officeDetails = officeRequest.getOfficeDetails();
        office.setRegion(officeDetails.getRegion());
        office.setTarget(officeDetails.getTarget());

        LOG.info("createOffice, office, that was created after={}", office);
        return office;
    }

}
