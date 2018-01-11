package com.io.invoices.invoiceshibernate.facture;

import com.io.invoices.invoiceshibernate.firm.FirmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactureService {
    @Autowired
    FactureRepository factureRepository;

    @Autowired
    FirmRepository firmRepository;

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
            throw new IllegalArgumentException("Bad company id!");
        }

        facture.setFirm(firmRepository.findOne(firmId));
        factureRepository.save(facture);
    }

    public void deleteFacture(Integer factureId) {
        factureRepository.delete(factureId);
    }

    public void updateFacture(int factureId, Facture facture) {
        if (!factureRepository.exists(factureId)) {
            throw new IllegalArgumentException("Bad facture id!");
        }

        Facture dbFacture = factureRepository.findOne(factureId);
        dbFacture.setIssueDate(facture.getIssueDate());
        dbFacture.setClient(facture.getClient());
        dbFacture.setIssuer(facture.getIssuer());
        dbFacture.setNumber(facture.getNumber());
        dbFacture.setPaymentDate(facture.getPaymentDate());
        dbFacture.setPlace(facture.getPlace());
        dbFacture.setProducts(facture.getProducts());
        dbFacture.setPaid(facture.getPaid());
        dbFacture.setPaymentMethod(facture.getPaymentMethod());

        factureRepository.save(dbFacture);
    }

/*    public void addFacture(String ownerId, Facture facture) {
        if (!userRepository.exists(Integer.parseInt(ownerId)))
            throw new IllegalArgumentException("Usery does not exist!");

        Usery usery = new Usery(Integer.parseInt(ownerId), "", "" ,"");
        facture.setUsery(usery);
        factureRepository.save(facture);
    }*/
}
