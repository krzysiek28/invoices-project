package web.mvc.domain;

public class Product {
    private Integer id;
    private Usery user;
    private String name;

    public Product(){}

    public Product(Usery user, String name) {
        this.user = user;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usery getUser() {
        return user;
    }

    public void setUser(Usery user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/*
    product_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    name VARCHAR NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (product_id, user_id)
*/
