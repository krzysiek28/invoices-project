package web.mvc.domain;

public class FactureProduct {

    private Integer id;
    private Facture facture;
    private User user;
    private Product product;

    public FactureProduct(){}

    public FactureProduct(Facture facture, User user, Product product) {
        this.facture = facture;
        this.user = user;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

/*
    facture_product_id INTEGER NOT NULL,
    facture_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    CONSTRAINT facture_product_pk PRIMARY KEY (facture_product_id, facture_id, user_id)*/