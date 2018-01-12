package web.mvc.domain;

public class ProductEntry {

    private Integer id;
    private Product product;
    private Float quantity;
    private int no;
    private Float netprice;
    private Float vat;
    private Float grossprice;

    public ProductEntry(Product product, Float quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductEntry(Product product, Float quantity, int no, Float netprice, Float vat, Float grossprice) {

        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.no = no;
        this.netprice = netprice;
        this.vat = vat;
        this.grossprice = grossprice;
    }

    public ProductEntry() {
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

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Float getNetprice() {
        return netprice;
    }

    public void setNetprice(Float netprice) {
        this.netprice = netprice;
    }

    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    public Float getGrossprice() {
        return grossprice;
    }

    public void setGrossprice(Float grossprice) {
        this.grossprice = grossprice;
    }


}
