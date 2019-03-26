package repository;


import model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Set;

public interface OfficeRepository extends JpaRepository<Office, BigDecimal> {

    Set<Office> findByCityStartingWithIgnoreCase(String city);
}
