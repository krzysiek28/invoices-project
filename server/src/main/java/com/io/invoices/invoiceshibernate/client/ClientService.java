package com.io.invoices.invoiceshibernate.client;

import com.io.invoices.invoiceshibernate.firm.FirmRepository;
import org.jsoup.Jsoup;
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
            throw new IllegalArgumentException("Niepoprawne id firmy!");

        client.setOwner(firmRepository.findOne(ownerId));
        client.stripTags();
        if (!client.isCorrect())
            throw new IllegalArgumentException("Podano niepoprawne dane!");

        clientRepository.save(client);
    }

    public List<Client> getAllClients(Integer ownerId) {
        if (!firmRepository.exists(ownerId))
            throw new IllegalArgumentException("Niepoprawne id firmy!");

        List<Client> allClients = new ArrayList<>();
        clientRepository.findByOwnerId(ownerId)
                .forEach(allClients::add);
        return allClients;
    }

    public void updateClient(Integer clientId, Client client) {
        if (!clientRepository.exists(clientId))
            throw new IllegalArgumentException("Klient nie istnieje!");

        Client dbClient = clientRepository.findOne(clientId);
        client.stripTags();
        if (!client.isCorrect())
            throw new IllegalArgumentException("Podano niepoprawne dane!");

        dbClient.setName(client.getName());
        dbClient.setAdditionalData(client.getAdditionalData());

        clientRepository.save(dbClient);

    }

    public void deleteClient(Integer clientId) {
        if (!clientRepository.exists(clientId))
            throw new IllegalArgumentException("Klient nie istnieje!");

        clientRepository.findOne(clientId).setOwner(null);
        clientRepository.delete(clientId);
    }
}
