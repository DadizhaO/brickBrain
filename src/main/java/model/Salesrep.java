package model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "SALESREPS", schema = "MA_STUDENT")
public class Salesrep implements Serializable {

    @Id
    @Column(name = "EMPL_NUM")
    private BigDecimal emplNum;
    @Column
    private String name;
    @Column
    private BigDecimal age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REP_OFFICE")
    private Office repOffice;
    @Column
    private String title;
    @Column(name = "HIRE_DATE")
    private LocalDate hireDate;
    @Column
    private BigDecimal manager;
    @Column
    private BigDecimal quota;
    @Column
    private BigDecimal sales;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mgr")
    private Set<Office> offices;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "custRep")
    private Set<Customer> customers;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rep")
    private Set<Order> orders;

    public Salesrep() {
    }

    public Salesrep(BigDecimal emplNum, String name, BigDecimal age, Office repOffice, String title, LocalDate hireDate, BigDecimal manager, BigDecimal quota, BigDecimal sales, Set<Office> offices, Set<Customer> customers, Set<Order> orders) {
        this.emplNum = emplNum;
        this.name = name;
        this.age = age;
        this.repOffice = repOffice;
        this.title = title;
        this.hireDate = hireDate;
        this.manager = manager;
        this.quota = quota;
        this.sales = sales;
        this.offices = offices;
        this.customers = customers;
        this.orders = orders;
    }

    public Set<Office> getOffices() {
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public BigDecimal getEmplNum() {
        return emplNum;
    }

    public void setEmplNum(BigDecimal emplNum) {
        this.emplNum = emplNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public Office getRepOffice() {
        return repOffice;
    }

    public void setRepOffice(Office repOffice) {
        this.repOffice = repOffice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public BigDecimal getManager() {
        return manager;
    }

    public void setManager(BigDecimal manager) {
        this.manager = manager;
    }

    public BigDecimal getQuota() {
        return quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Salesrep{" +
                "emplNum=" + emplNum +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", repOffice=" + repOffice.getClass() +
                ", title='" + title + '\'' +
                ", hireDate=" + hireDate +
                ", manager=" + manager +
                ", quota=" + quota +
                ", sales=" + sales +
                ", offices=" + offices +
                ", customers=" + customers +
                ", orders=" + orders +
                '}';
    }
}
