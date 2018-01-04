package com.io.invoices.invoiceshibernate.facture;

import com.io.invoices.invoiceshibernate.user.Usery;
import com.io.invoices.invoiceshibernate.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactureService {
    @Autowired
    FactureRepository factureRepository;

    @Autowired
    UserRepository userRepository;

    public List<Facture> getAllFactures(String ownerId) {
        List<Facture> factures = new ArrayList<>();
        factureRepository.findByUseryId(Integer.parseInt(ownerId))
                .forEach(factures::add);
        return factures;
    }

/*    public void addFacture(String ownerId, Facture facture) {
        if (!userRepository.exists(Integer.parseInt(ownerId)))
            throw new IllegalArgumentException("Usery does not exist!");

        Usery usery = new Usery(Integer.parseInt(ownerId), "", "" ,"");
        facture.setUsery(usery);
        factureRepository.save(facture);
    }*/
}
