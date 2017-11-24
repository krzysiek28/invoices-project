package com.io.invoices.invoiceshibernate.firmUsers;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FirmUsersRepository extends CrudRepository<FirmUsers, Integer>{
    List<FirmUsers> findFirmUsersByFirm_Name(String name);
}
