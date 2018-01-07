package com.io.invoices.invoiceshibernate.facture;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FactureRepository extends CrudRepository<Facture, Integer>{
    public Facture findById(Integer id);
    public List<Facture> findByFirmId(Integer firmId);
}
