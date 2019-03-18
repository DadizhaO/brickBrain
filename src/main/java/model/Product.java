package model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCTS", schema = "MA_STUDENT")
public class Product implements Serializable {

    @Id
    @Column(name = "PRODUCT_ID")
    private String productId;
    @Column(name = "MFR_ID")
    private String mfrId;
    @Column
    private String description;
    @Column
    private BigDecimal price;
    @Column(name = "QTY_ON_HAND")
    private BigDecimal qtyOnHand;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<Order> orderses = new HashSet<>(0);


    public Product() {

    }

    public Product(String productId) {
        this.productId = productId;
    }

    public Product(String productId, String mfrId, String description, BigDecimal price, BigDecimal qtyOnHand) {
        this.productId = productId;
        this.mfrId = mfrId;
        this.description = description;
        this.price = price;
        this.qtyOnHand = qtyOnHand;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMfrId() {
        return mfrId;
    }

    public void setMfrId(String mfrId) {
        this.mfrId = mfrId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(BigDecimal qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }


    public Set<Order> getOrderses() {
        return orderses;
    }

    public void setOrderses(Set<Order> orderses) {
        this.orderses = orderses;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", mfrId='" + mfrId + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}

