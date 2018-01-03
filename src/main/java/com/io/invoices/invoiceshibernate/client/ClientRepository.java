package com.io.invoices.invoiceshibernate.client;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client,Integer> {
    public List<Client> findByOwnerId(Integer ownerId);
}
