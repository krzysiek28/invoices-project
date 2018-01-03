package com.io.invoices.invoiceshibernate.client;

import com.io.invoices.invoiceshibernate.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public void addClient(String ownerId, Client client) {
        //todo: sprawdzić czy istnieje, ale niewykluczone że imie i nazwisko moze byc takie samo
        User user = new User(Integer.parseInt(ownerId), "", "", "");
        client.setOwner(user);
        clientRepository.save(client);
    }

    public List<Client> getAllClients(String ownerId) {
        List<Client> allClients = new ArrayList<>();
        clientRepository.findAll()
                .forEach(allClients::add);
        return allClients;
    }

    public void updateClient(String clientId, Client client) {
        if (!clientRepository.exists(Integer.parseInt(clientId))) {
            throw new IllegalArgumentException("Client does not exist!");
        }

        Client oldClient = clientRepository.findOne(Integer.parseInt(clientId));
        client.setId(oldClient.getId());
        clientRepository.save(client);
    }

    public void deleteClient(String clientId) {
        if (!clientRepository.exists(Integer.parseInt(clientId))) {
            throw new IllegalArgumentException("Client does not exist!");
        }

        clientRepository.delete(Integer.parseInt(clientId));
    }
}
