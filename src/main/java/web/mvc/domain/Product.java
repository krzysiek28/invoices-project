package web.mvc.domain;

public class Product {
    private Integer id;
    private Usery usery;
    private String name;
    private Float netUnitPrice;
    private Float vatRate;
    private String unit;
    private String currency;

    public Product(Usery usery, String name, Float netUnitPrice, Float vatRate, String unit, String currency) {
        this.usery = usery;
        this.name = name;
        this.netUnitPrice = netUnitPrice;
        this.vatRate = vatRate;
        this.unit = unit;
        this.currency = currency;
    }

    public Product(int id) {
        this.id = id;
    }

    public Product() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usery getUsery() {
        return usery;
    }

    public void setUsery(Usery usery) {
        this.usery = usery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getNetUnitPrice() {
        return netUnitPrice;
    }

    public void setNetUnitPrice(Float netUnitPrice) {
        this.netUnitPrice = netUnitPrice;
    }

    public Float getVatRate() {
        return vatRate;
    }

    public void setVatRate(Float vatRate) {
        this.vatRate = vatRate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
