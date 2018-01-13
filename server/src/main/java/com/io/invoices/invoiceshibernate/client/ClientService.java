package com.io.invoices.invoiceshibernate.client;

import com.io.invoices.invoiceshibernate.firm.FirmRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final FirmRepository firmRepository;

    public ClientService(ClientRepository clientRepository, FirmRepository firmRepository) {
        this.clientRepository = clientRepository;
        this.firmRepository = firmRepository;
    }

    public void addClient(Integer ownerId, Client client) {
        if (!firmRepository.exists(ownerId))
            throw new IllegalArgumentException("Bad company id!");

        client.setOwner(firmRepository.findOne(ownerId));
        clientRepository.save(client);
    }

    public List<Client> getAllClients(Integer ownerId) {
        if (!firmRepository.exists(ownerId))
            throw new IllegalArgumentException("Bad company id!");

        List<Client> allClients = new ArrayList<>();
        clientRepository.findByOwnerId(ownerId)
                .forEach(allClients::add);
        return allClients;
    }

    public void updateClient(Integer clientId, Client client) {
        if (!clientRepository.exists(clientId))
            throw new IllegalArgumentException("Client does not exist!");

        Client dbClient = clientRepository.findOne(clientId);
        dbClient.setName(client.getName());
        dbClient.setAdditionalData(client.getAdditionalData());

        clientRepository.save(dbClient);
    }

    public void deleteClient(Integer clientId) {
        if (!clientRepository.exists(clientId))
            throw new IllegalArgumentException("Client does not exist!");

        clientRepository.findOne(clientId).setOwner(null);
        clientRepository.delete(clientId);
    }
}
