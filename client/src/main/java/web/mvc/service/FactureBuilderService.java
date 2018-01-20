package web.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.mvc.domain.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FactureBuilderService {
    private Facture facture;

    @Autowired
    ProductService productService;

    public FactureBuilderService() {
        this.facture = new Facture();
        this.facture.setProducts(new ArrayList<ProductEntry>());
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public void newFacture() {
        this.facture = new Facture();
        this.facture.setProducts(new ArrayList<ProductEntry>());
    }

    public void setClientData(String clientId) {
        Client client = new Client();
        client.setId(Integer.parseInt(clientId));
        facture.setClient(client);
    }

    public void setBankAccount(String accountNumber) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBankAccount(accountNumber);
        facture.setBankAccount(bankAccount);
    }

    public void setFactureData(String number, String place) {
        facture.setNumber(number);
        facture.setPlace(place);
    }

    public void setDates(String issueDate, String paymentDate) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date issue = parser.parse(issueDate);
        Date payment = parser.parse(paymentDate);
        facture.setIssueDate(new java.sql.Date(issue.getTime()));
        facture.setPaymentDate(new java.sql.Date(payment.getTime()));
    }

    public void setPayment(String paymentMethod, String paid, String currency) {
        facture.setPaymentMethod(paymentMethod);
        facture.setPaid(Float.parseFloat(paid));
        facture.setCurrency(currency);
    }

    public void addProduct(int productId, Float quantity) {
        List<ProductEntry> products = facture.getProducts();
        products.add(
                new ProductEntry(
                        new Product(productId),quantity
        ));

    }

    public void deleteProduct(int i) {
        List<ProductEntry> products = facture.getProducts();
        products.removeIf(t->t.getProduct().getId() == i);
    }

    public void setCurrency(String currency) {
        this.facture.setCurrency(currency);
    }

    public void calculatePrices() throws IOException, URISyntaxException {
        List<ProductEntry> products = facture.getProducts();
        double total = 0.0;
        for (ProductEntry product : products) {
            Product unitproduct = productService.getProduct(product.getProduct().getId());
            product.setNetprice(
                    new Float(Math.round((unitproduct.getNetUnitPrice()*product.getQuantity()) * 100.0) / 100.0)
            );
            product.setVat( new Float(
                            Math.round(unitproduct.getVatRate()*product.getNetprice() * 100.0) /100.0

                    )
            );
            product.setGrossprice(new Float(
                    Math.round(product.getNetprice()+product.getVat() * 100.0) / 100.0
            ));
            total += product.getGrossprice();
        }
        facture.setTotal( new Float( Math.round(total * 100.0) / 100.0 ));
    }
}
