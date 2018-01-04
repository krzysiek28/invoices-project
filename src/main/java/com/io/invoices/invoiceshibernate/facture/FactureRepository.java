package com.io.invoices.invoiceshibernate.facture;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FactureRepository extends CrudRepository<Facture, Integer>{
    public List<Facture> findByUseryId(Integer userId);
}
