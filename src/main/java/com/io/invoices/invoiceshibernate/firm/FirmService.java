package com.io.invoices.invoiceshibernate.firm;

import com.io.invoices.invoiceshibernate.bankAccount.BankAccountRepository;
import com.io.invoices.invoiceshibernate.client.ClientRepository;
import com.io.invoices.invoiceshibernate.product.ProductRepository;
import com.io.invoices.invoiceshibernate.user.ApplicationUserRepository;
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

    public FirmService(FirmRepository firmRepository, ClientRepository clientRepository, ProductRepository productRepository, BankAccountRepository bankAccountRepository, ApplicationUserRepository userRepository) {
        this.firmRepository = firmRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    public void addFirm(Integer ownerId, Firm firm) {
        if (!userRepository.exists(ownerId)) {
            throw new IllegalArgumentException("Company owner does not exist!");
        }

        firm.setOwner(userRepository.findOne(ownerId));
        firmRepository.save(firm);
    }

    public List<Firm> getFirms(Integer ownerId) {
        if (!userRepository.exists(ownerId)) {
            throw new IllegalArgumentException("Company owner does not exist!");
        }

        List<Firm> firms = new ArrayList<>();
        firmRepository.findByOwnerId(ownerId)
                .forEach(firms::add);
        return firms;
    }

    public void updateFirm(Integer firmId, Firm firm) {
        if (!firmRepository.exists(firmId)) {
            throw new IllegalArgumentException("Company does not exist!");
        }

        Firm dbFirm = firmRepository.findOne(firmId);
        dbFirm.setEmail(firm.getEmail());
        dbFirm.setName(firm.getName());
        dbFirm.setNip(firm.getNip());
        dbFirm.setPhone(firm.getPhone());
        dbFirm.setPlace(firm.getPlace());
        firmRepository.save(dbFirm);
    }

    public void deleteFirm(Integer firmId) {
        if (!firmRepository.exists(firmId)) {
            throw new IllegalArgumentException("Company does not exist!");
        }

        System.out.println(firmId);
        productRepository.deleteProductsByOwnerId(firmId);
        clientRepository.deleteClientsByOwnerId(firmId);
        bankAccountRepository.deleteBankAccountsByFirmId(firmId);

        firmRepository.findOne(firmId).setOwner(null);
        firmRepository.delete(firmId);
    }
}
