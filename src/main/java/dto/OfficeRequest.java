package dto;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class OfficeRequest {

    @NotNull(message = "1")
    @Positive(message = "1")
    private BigDecimal officeId;

    @NotNull(message = "2")
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "2")
    private String city;

    @NotNull(message = "3")
    @Positive(message = "3")
    private BigDecimal sales;

    @Valid
    private OfficeDetails officeDetails;

    public BigDecimal getOfficeId() {
        return officeId;
    }

    public void setOfficeId(BigDecimal officeId) {
        this.officeId = officeId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public OfficeDetails getOfficeDetails() {
        return officeDetails;
    }

    public void setOfficeDetails(OfficeDetails officeDetails) {
        this.officeDetails = officeDetails;
    }
}
