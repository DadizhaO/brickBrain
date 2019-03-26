package service;

import model.Office;

import java.math.BigDecimal;
import java.util.Set;

public interface OfficeService {
    Set<Office> getAllOffices();

    Office findOfficeById(BigDecimal id);

    void insertOffice(Office office);

    void updateOffice(Office office);

    void deleteOffice(BigDecimal id);

    Set<Office> findByCityStartingWithIgnoreCase(String city);
}
