package repository;


import model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface OfficeRepository extends JpaRepository<Office, BigDecimal> {

    List <Office> findByCityIgnoreCase(String city);
}
