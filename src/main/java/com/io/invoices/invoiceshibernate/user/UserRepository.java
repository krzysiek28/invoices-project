package com.io.invoices.invoiceshibernate.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Usery, Integer> {
    public List<Usery> findByName(String name);

}
