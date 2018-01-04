package web.mvc.domain;

public class Client {
    private Integer id;
    private String name;
    private String additionalData;
    private Usery owner;

    public Client() {
    }

    public Client(String name, String additionalData, Usery owner) {
        this.name = name;
        this.additionalData = additionalData;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public Usery getOwner() {
        return owner;
    }

    public void setOwner(Usery owner) {
        this.owner = owner;
    }
}
