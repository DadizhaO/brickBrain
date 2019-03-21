package service.impl;

import exception.DeleteException;
import model.Office;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import repository.OfficeRepository;
import service.OfficeService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OfficeServiceImpl implements OfficeService {
    private static final Logger LOG = LogManager.getLogger(OfficeServiceImpl.class);

    @Autowired
    private OfficeRepository officeRepository;

    @Override
    public Set<Office> getAllOffices() {
        return new HashSet<>(officeRepository.findAll());
    }

    @Override
    public Office findOfficeById(BigDecimal id) {
        return officeRepository.findById(id).orElse(null);
    }

    @Override
    public void insertOffice(Office office) {
        officeRepository.saveAndFlush(office);
    }

    @Override
    public void updateOffice(Office office) {
        officeRepository.saveAndFlush(office);
    }

    @Override
    public void deleteOffice(BigDecimal id) {
        try {
            officeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOG.warn("Cannot delete Office with id{}, because it don't present", id);
            throw new DeleteException("Cannot delete Office by Id=" + id + ", because it don't present");
        }
    }

    @Override
    public List<Office> findByCityIgnoreCase(String city) {
        return officeRepository.findByCityIgnoreCase(city);
    }

}
