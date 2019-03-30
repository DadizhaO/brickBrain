package service;

import dto.OfficeRequest;
import model.Office;

public interface OfficeCreator {
    Office createOffice(OfficeRequest officeRequest);
}
