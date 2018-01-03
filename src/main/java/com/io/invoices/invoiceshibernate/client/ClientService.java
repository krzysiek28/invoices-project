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
}
