package com.io.invoices.invoiceshibernate.productentry;

import com.io.invoices.invoiceshibernate.product.Product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ProductEntry {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    private Product product;

    public ProductEntry(Product product, Float quantity, int no) {
        this.product = product;
        this.quantity = quantity;
        this.no = no;
    }

    private Float quantity;
    private int no;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public ProductEntry() {

    }

    public ProductEntry(Product product, Float quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }
}
