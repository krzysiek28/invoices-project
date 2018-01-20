package com.io.invoices.invoiceshibernate.firm;

import com.io.invoices.invoiceshibernate.bankAccount.BankAccountRepository;
import com.io.invoices.invoiceshibernate.client.ClientRepository;
import com.io.invoices.invoiceshibernate.facture.FactureRepository;
import com.io.invoices.invoiceshibernate.product.ProductRepository;
import com.io.invoices.invoiceshibernate.user.ApplicationUserRepository;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirmService {

    private final FirmRepository firmRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ApplicationUserRepository userRepository;
    private final FactureRepository factureRepository;

    public FirmService(FirmRepository firmRepository, ClientRepository clientRepository, ProductRepository productRepository, BankAccountRepository bankAccountRepository, ApplicationUserRepository userRepository, FactureRepository factureRepository) {
        this.firmRepository = firmRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
        this.factureRepository = factureRepository;
    }

    public void addFirm(Integer ownerId, Firm firm) {
        if (!userRepository.exists(ownerId)) {
            throw new IllegalArgumentException("Niepoprawny wlasciciel firmy!");
        }

        firm.setOwner(userRepository.findOne(ownerId));

        firm.stripTags();
        if (!firm.isCorrect())
            throw new IllegalArgumentException("Podano niepoprawne dane!");

        firmRepository.save(firm);
    }

    public List<Firm> getFirms(Integer ownerId) {
        if (!userRepository.exists(ownerId)) {
            throw new IllegalArgumentException("Niepoprawny wlasciciel firmy!");
        }

        List<Firm> firms = new ArrayList<>();
        firmRepository.findByOwnerId(ownerId)
                .forEach(firms::add);
        return firms;
    }

    public void updateFirm(Integer firmId, Firm firm) {
        if (!firmRepository.exists(firmId)) {
            throw new IllegalArgumentException("Podana firma nie istnieje!");
        }

        firm.stripTags();
        if (!firm.isCorrect())
            throw new IllegalArgumentException("Podano niepoprawne dane!");

        Firm dbFirm = firmRepository.findOne(firmId);
        dbFirm.setPlace(firm.getPlace());
        dbFirm.setPhone(firm.getPhone());
        dbFirm.setNip(firm.getNip());
        dbFirm.setName(firm.getName());
        dbFirm.setEmail(firm.getEmail());

        firmRepository.save(dbFirm);
    }

    public void deleteFirm(Integer firmId) {
        if (!firmRepository.exists(firmId)) {
            throw new IllegalArgumentException("Podana firma nie istnieje!");
        }

        System.out.println(firmId);
        factureRepository.deleteFacturesByFirmId(firmId);
        productRepository.deleteProductsByOwnerId(firmId);
        clientRepository.deleteClientsByOwnerId(firmId);
        bankAccountRepository.deleteBankAccountsByFirmId(firmId);

        firmRepository.findOne(firmId).setOwner(null);
        firmRepository.delete(firmId);
    }
}
