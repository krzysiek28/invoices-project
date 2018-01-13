package com.io.invoices.invoiceshibernate.facture;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FactureRepository extends CrudRepository<Facture, Integer> {
    List<Facture> findByFirmId(Integer firmId);

    @Transactional
    long deleteFacturesByFirmId(Integer firmId);

}
