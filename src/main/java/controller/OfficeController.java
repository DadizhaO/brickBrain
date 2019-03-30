package controller;

import dto.OfficeRequest;
import exception.AbsentException;
import exception.UpdateException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import model.Office;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.OfficeCreator;
import service.OfficeService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/office")
public class OfficeController {

    private static final Logger LOG = LogManager.getLogger(OfficeController.class);

    @Autowired
    private OfficeService officeService;

    @Autowired
    private OfficeCreator officeCreator;

    @PostMapping
    @ApiOperation(authorizations = {@Authorization(value = "basicAuth")}, value = "addOffice")
    public void addOffice(@Valid @RequestBody OfficeRequest officeRequest) {
        LOG.info("addOffice start, officeRequest={}", officeRequest.toString());
        Office office = officeCreator.createOffice(officeRequest);
        officeService.insertOffice(office);
        LOG.info("addOffice end");
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Office getOfficeById(@PathVariable("id") int id) {
        LOG.info("getOfficeById start, id={}", id);
        Office result = officeService.findOfficeById(BigDecimal.valueOf(id));
        if (result == null) {
            throw new AbsentException("There is no such office");
        }
        LOG.info("getOfficeById end");
        return result;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(authorizations = {@Authorization(value = "basicAuth")}, value = "deleteOfficeById")
    public void deleteOfficeById(@PathVariable("id") int id) {
        LOG.info("deleteOfficeById start, id={}", id);
        officeService.deleteOffice(BigDecimal.valueOf(id));
        LOG.info("deleteOfficeById end");
    }

    @PutMapping("/{id}")
    @ApiOperation(authorizations = {@Authorization(value = "basicAuth")}, value = "updateOfficeById")
    public void updateOfficeById(@PathVariable("id") int id, @RequestParam("sales") Integer sales) {
        LOG.info("updateOfficeById start id={}, sales={}", id, sales);
        Office office = officeService.findOfficeById(BigDecimal.valueOf(id));
        if (Objects.isNull(office)) {
            LOG.warn("Update failed");
            throw new UpdateException("Could not update Office id=" + id + ", because it does not exist");
        } else {
            office.setSales(BigDecimal.valueOf(sales));
            officeService.updateOffice(office);
        }
        LOG.info("Update Office completed.");
    }

    @GetMapping
    public @ResponseBody
    Set<Office> getOfficeByNameStartWith(@RequestParam(value = "name", required = false) String name) {
        LOG.info("getOfficeByNameStartWith start, name={}", name);
        if (Objects.isNull(name)) {
            LOG.debug("use getAllOffice");
            return officeService.getAllOffices();
        } else {
            Set<Office> result = officeService.findByCityStartingWithIgnoreCase(name);
            if (result.isEmpty()) {
                throw new AbsentException("There is no such office, Try other name");
            }
            LOG.info("getOfficeByNameStartWith end");
            return result;
        }
    }
}
