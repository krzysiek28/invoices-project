package web.mvc.domain;


public class Client {
    private Integer id;
    private User user;
    private Firm firm;

    public Client(){}

    public Client(User user, Firm firm) {
        this.user = user;
        this.firm = firm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }
}

/*
        client_id INTEGER NOT NULL,
        user_id INTEGER NOT NULL,
        firm_id INTEGER NOT NULL,
        CONSTRAINT client_pk PRIMARY KEY (client_id, user_id, firm_id)
*/
