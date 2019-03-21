package controller;

import model.Office;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.OfficeService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/office")
public class OfficeController {

    private static final Logger LOG = LogManager.getLogger(OfficeController.class);

    @Autowired
    private OfficeService officeService;

    @GetMapping("/{id}")
    public @ResponseBody
    Office getOfficeById(@PathVariable("id") int id) {
        LOG.info("getOfficeById start, id={}", id);
        Office result = officeService.findOfficeById(BigDecimal.valueOf(id));
        LOG.info("getOfficeById end");
        return result;
    }


}
