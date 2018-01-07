package com.io.invoices.invoiceshibernate.firm;

import com.io.invoices.invoiceshibernate.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirmService {

    @Autowired
    FirmRepository firmRepository;

    @Autowired
    ApplicationUserRepository userRepository;

    public void addFirm(Integer ownerId, Firm firm) {
        if (!userRepository.exists(ownerId)) {
            throw new IllegalArgumentException("Company owner does not exist!");
        }

        firm.setOwner(userRepository.findOne(ownerId));
        firmRepository.save(firm);
    }

    public List<Firm> getFirms(Integer ownerId) {
        List<Firm> firms = new ArrayList<>();
        firmRepository.findByOwnerId(ownerId)
                .forEach(firms::add);
        return firms;
    }

    public void updateFirm(Integer firmId, Firm firm) {
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

        firmRepository.findOne(firmId).setOwner(null);
        firmRepository.delete(firmId);
    }
}
