
package model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "OFFICES", schema = "MA_STUDENT")
public class Office implements Serializable {

    @Id
    @Column(name = "OFFICE")
    private BigDecimal officeId;
    @Column
    private String city;
    @Column
    private String region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MGR")
    private Salesrep mgr;
    @Column
    private BigDecimal target;
    @Column
    private BigDecimal sales;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "repOffice")
    private Set<Salesrep> salesreps;

    public Office() {
    }

    public Office(BigDecimal office, String city, String region, Salesrep mgr, BigDecimal target, BigDecimal sales) {
        this.officeId = office;
        this.city = city;
        this.region = region;
        this.mgr = mgr;
        this.target = target;
        this.sales = sales;
    }

    public Office(BigDecimal officeId, String city, String region, Salesrep mgr, BigDecimal target, BigDecimal sales, Set<Salesrep> salesreps) {
        this.officeId = officeId;
        this.city = city;
        this.region = region;
        this.mgr = mgr;
        this.target = target;
        this.sales = sales;
        this.salesreps = salesreps;
    }


    public Set<Salesrep> getSalesreps() {
        return salesreps;
    }

    public void setSalesreps(Set<Salesrep> salesreps) {
        this.salesreps = salesreps;
    }

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Salesrep getMgr() {
        return mgr;
    }

    public void setMgr(Salesrep mgr) {
        this.mgr = mgr;
    }

    public BigDecimal getTarget() {
        return target;
    }

    public void setTarget(BigDecimal target) {
        this.target = target;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Office{" +
                "officeId=" + officeId +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", mgr=" + mgr.getClass() +
                ", target=" + target +
                ", sales=" + sales +
                ", salesreps=" + salesreps +
                '}';
    }
}

