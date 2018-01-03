package com.io.invoices.invoiceshibernate.facture;

import com.io.invoices.invoiceshibernate.user.User;
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
        factureRepository.findByUserId(Integer.parseInt(ownerId))
                .forEach(factures::add);
        return factures;
    }

    public void addFacture(String ownerId, Facture facture) {
        if (!userRepository.exists(Integer.parseInt(ownerId)))
            throw new IllegalArgumentException("User does not exist!");

        User user = new User(Integer.parseInt(ownerId), "", "" ,"");
        facture.setUser(user);
        factureRepository.save(facture);
    }
}
