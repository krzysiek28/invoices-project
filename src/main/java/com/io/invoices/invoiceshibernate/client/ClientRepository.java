package com.io.invoices.invoiceshibernate.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findByOwnerId(Integer ownerId);
    @Transactional
    long deleteClientsByOwnerId(Integer ownerId);
}
