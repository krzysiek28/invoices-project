package com.io.invoices.invoiceshibernate.facture;

import com.io.invoices.invoiceshibernate.bankAccount.BankAccount;
import com.io.invoices.invoiceshibernate.bankAccount.BankAccountRepository;
import com.io.invoices.invoiceshibernate.client.Client;
import com.io.invoices.invoiceshibernate.client.ClientRepository;
import com.io.invoices.invoiceshibernate.firm.FirmRepository;
import com.io.invoices.invoiceshibernate.product.Product;
import com.io.invoices.invoiceshibernate.product.ProductRepository;
import com.io.invoices.invoiceshibernate.productentry.ProductEntry;
import com.io.invoices.invoiceshibernate.productentry.ProductEntryRepository;
import com.io.invoices.invoiceshibernate.user.ApplicationUser;
import com.io.invoices.invoiceshibernate.user.ApplicationUserRepository;
import io.jsonwebtoken.Jwts;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.io.invoices.invoiceshibernate.security.SecurityUtils.SECRET;
import static com.io.invoices.invoiceshibernate.security.SecurityUtils.TOKEN_PREFIX;

@Service
public class FactureService {

    private final FactureRepository factureRepository;
    private final ClientRepository clientRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ProductRepository productRepository;
    private final ProductEntryRepository productEntryRepository;
    private final FirmRepository firmRepository;
    private final ApplicationUserRepository applicationUserRepository;

    public FactureService(FactureRepository factureRepository, ClientRepository clientRepository, BankAccountRepository bankAccountRepository, ProductRepository productRepository, ProductEntryRepository productEntryRepository, FirmRepository firmRepository, ApplicationUserRepository applicationUserRepository) {
        this.factureRepository = factureRepository;
        this.clientRepository = clientRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.productRepository = productRepository;
        this.productEntryRepository = productEntryRepository;
        this.firmRepository = firmRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    public List<Facture> getAllFactures(Integer firmId) {
        List<Facture> factures = new ArrayList<>();
        factureRepository.findByFirmId(firmId)
                .forEach(factures::add);
        return factures;
    }

    public Facture getFacture(Integer factureId) {
        Facture facture = factureRepository.findOne(factureId);
        return facture;
    }

    public void addFacture(Integer firmId, Facture facture) {
        if (!firmRepository.exists(firmId)) {
            throw new IllegalArgumentException("Niepoprawne id firmy!");
        }

        facture.stripTags();
        if (!facture.isCorrect())
            throw new IllegalArgumentException("Podano niepoprawne dane!");

        Client client = clientRepository.findOne(facture.getClient().getId());
        Client historyClient = new Client();
        historyClient.setName(client.getName());
        historyClient.setAdditionalData(client.getAdditionalData());
        clientRepository.save(historyClient);
        facture.setClient(historyClient);

        BankAccount bankAccount = bankAccountRepository.findOne(facture.getBankAccount().getBankAccount());
        BankAccount historyAccount = new BankAccount();
        String historyString = bankAccount.getBankAccount() + " ";
        historyAccount.setBankAccount(historyString);
        historyAccount.setAdditionalData(bankAccount.getAdditionalData());
        bankAccountRepository.save(historyAccount);
        facture.setBankAccount(historyAccount);


        if (facture.getProducts() != null) {
            for (ProductEntry productEntry : facture.getProducts()) {
                productEntryRepository.save(productEntry);

                Product product = productRepository.findOne(productEntry.getProduct().getId());
                Product historyProduct = new Product(); //historyproduct is current copy of product made to prevent future product changes to reflect on issued invoices

                historyProduct.setCurrency(product.getCurrency());
                historyProduct.setUnit(product.getUnit());
                historyProduct.setVatRate(product.getVatRate());
                historyProduct.setName(product.getName());
                historyProduct.setNetUnitPrice(product.getNetUnitPrice());
                historyProduct.setVatInfo(product.getVatInfo());
                productEntry.setProduct(historyProduct);

                productRepository.save(historyProduct);
            }
        }

        facture.setFirm(firmRepository.findOne(firmId));
        factureRepository.save(facture);
    }

    public void deleteFacture(Integer factureId) {
        if (!factureRepository.exists(factureId))
            throw new IllegalArgumentException("Faktura o podanym id nie istnieje!");

        factureRepository.delete(factureId);
    }

    public void updateFacture(int factureId, Facture facture) {
        if (!factureRepository.exists(factureId))
            throw new IllegalArgumentException("Faktura o podanym id nie istnieje!");

        facture.stripTags();
        if (!facture.isCorrect())
            throw new IllegalArgumentException("Podano niepoprawne dane!");


        Facture dbFacture = factureRepository.findOne(factureId);
        dbFacture.setIssueDate(facture.getIssueDate());
        dbFacture.setClient(facture.getClient());
        dbFacture.setBankAccount(facture.getBankAccount());
        dbFacture.setIssuer(facture.getIssuer());
        dbFacture.setNumber(facture.getNumber());
        dbFacture.setPaymentDate(facture.getPaymentDate());
        dbFacture.setPlace(facture.getPlace());
        dbFacture.setProducts(facture.getProducts());
        dbFacture.setPaid(facture.getPaid());
        dbFacture.setPaymentMethod(facture.getPaymentMethod());
        dbFacture.setToPay(facture.getToPay());
        dbFacture.setTotal(facture.getTotal());
        dbFacture.setCurrency(facture.getCurrency());

        factureRepository.save(dbFacture);
    }

    public String getIssuer(String token) {
        String username = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        return user.getPersonalData();
    }
}
