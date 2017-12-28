package com.io.invoices.invoiceshibernate.userFirm;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FirmUsersRepository extends CrudRepository<FirmUsers, Integer>{
    List<FirmUsers> findFirmUsersByFirm_Name(String name);
}
