package com.io.invoices.invoiceshibernate.client;

import com.io.invoices.invoiceshibernate.firm.FirmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    FirmRepository firmRepository;

    public void addClient(Integer ownerId, Client client) {
        if (!firmRepository.exists(ownerId)) {
            throw new IllegalArgumentException("Bad company id!");
        }

        client.setOwner(firmRepository.findOne(ownerId));
        clientRepository.save(client);
    }

    public List<Client> getAllClients(Integer ownerId) {
        List<Client> allClients = new ArrayList<>();
        clientRepository.findByOwnerId(ownerId)
                .forEach(allClients::add);
        return allClients;
    }

    public void updateClient(Integer clientId, Client client) {
        if (!clientRepository.exists(clientId)) {
            throw new IllegalArgumentException("Client does not exist!");
        }

        Client dbClient = clientRepository.findOne(clientId);
        dbClient.setName(client.getName());
        dbClient.setAdditionalData(client.getAdditionalData());

        clientRepository.save(dbClient);
    }

//    public void deleteClient(String clientId) {
//        if (!clientRepository.exists(Integer.parseInt(clientId))) {
//            throw new IllegalArgumentException("Client does not exist!");
//        }
//
//        clientRepository.findOne(Integer.parseInt(clientId)).setOwner(null);
//        clientRepository.delete(Integer.parseInt(clientId));
//    }

    public void deleteClient(Integer clientId) {
        if (!clientRepository.exists(clientId)) {
            throw new IllegalArgumentException("Client does not exist!");
        }
        clientRepository.findOne(clientId).setOwner(null);
        clientRepository.delete(clientId);
    }
}
