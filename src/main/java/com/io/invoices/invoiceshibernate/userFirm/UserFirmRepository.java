package com.io.invoices.invoiceshibernate.userFirm;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserFirmRepository extends CrudRepository<UserFirm, Integer>{
    List<UserFirm> findFirmUsersByFirm_Name(String name);
}
