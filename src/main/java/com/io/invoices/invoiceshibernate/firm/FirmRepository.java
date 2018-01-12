package com.io.invoices.invoiceshibernate.firm;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FirmRepository extends CrudRepository<Firm, Integer> {
    public List<Firm> findByOwnerId(Integer ownerId);
}
