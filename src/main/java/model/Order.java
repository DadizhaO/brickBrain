package model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ORDERS", schema = "MA_STUDENT")
public class Order implements Serializable {

    @Id
    @Column(name = "ORDER_NUM")
    private BigDecimal orderNum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT")
    private Product product;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column
    private String mfr;
    @Column
    private BigDecimal qty;
    @Column
    private BigDecimal amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST")
    private Customer cust;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REP")
    private Salesrep rep;

    public Order() {
    }

    public Order(BigDecimal orderNum, Product product) {
        this.orderNum = orderNum;
        this.product = product;
    }

    public Order(BigDecimal orderNum) {
        this.orderNum = orderNum;
    }

    public Order(BigDecimal orderNum, Product product, LocalDate orderDate, String mfr, BigDecimal qty, BigDecimal amount) {
        this.orderNum = orderNum;
        this.product = product;
        this.orderDate = orderDate;
        this.mfr = mfr;
        this.qty = qty;
        this.amount = amount;
    }

    public Order(BigDecimal orderNum, Product product, LocalDate orderDate, String mfr, BigDecimal qty, BigDecimal amount, Customer cust, Salesrep rep) {
        this.orderNum = orderNum;
        this.product = product;
        this.orderDate = orderDate;
        this.mfr = mfr;
        this.qty = qty;
        this.amount = amount;
        this.cust = cust;
        this.rep = rep;
    }

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    public Salesrep getRep() {
        return rep;
    }

    public void setRep(Salesrep rep) {
        this.rep = rep;
    }

    public BigDecimal getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(BigDecimal orderNum) {
        this.orderNum = orderNum;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getMfr() {
        return mfr;
    }

    public void setMfr(String mfr) {
        this.mfr = mfr;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNum=" + orderNum +
                ", product=" + product.getMfrId() +
                ", orderDate=" + orderDate +
                ", mfr='" + mfr + '\'' +
                ", qty=" + qty +
                ", amount=" + amount +
                ", cust=" + cust.getCustNum() +
                ", rep=" + rep.getEmplNum() +
                '}';
    }
}


