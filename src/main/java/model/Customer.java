package model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "CUSTOMERS", schema = "MA_STUDENT")
public class Customer implements Serializable {
    @Id
    @Column(name = "cust_num")
    private BigDecimal custNum;
    @Column
    private String company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST_REP")
    private Salesrep custRep;
    @Column(name = "CREDIT_LIMIT")
    private
    BigDecimal creditLimit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cust")
    private Set<Order> orders;

    public Customer() {
    }

    public Customer(BigDecimal custNum, String company, Salesrep custRep, BigDecimal creditLimit, Set<Order> orders) {
        this.custNum = custNum;
        this.company = company;
        this.custRep = custRep;
        this.creditLimit = creditLimit;
        this.orders = orders;
    }

    public BigDecimal getCustNum() {
        return custNum;
    }

    public void setCustNum(BigDecimal custNum) {
        this.custNum = custNum;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Salesrep getCustRep() {
        return custRep;
    }

    public void setCustRep(Salesrep custRep) {
        this.custRep = custRep;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custNum=" + custNum +
                ", company='" + company + '\'' +
                ", custRep=" + custRep.getEmplNum() +
                ", creditLimit=" + creditLimit +
                ", orders=" + orders +
                '}';
    }
}
