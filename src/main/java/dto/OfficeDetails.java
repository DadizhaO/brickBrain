package dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class OfficeDetails {

    @NotNull(message = "4")
    @Pattern(regexp = "^[A-Za-z]{1,15}$", message = "4")
    private String region;

    @Min(value = 50, message = "5")
    private BigDecimal target;


    public OfficeDetails() {
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getTarget() {
        return target;
    }

    public void setTarget(BigDecimal target) {
        this.target = target;
    }
}
