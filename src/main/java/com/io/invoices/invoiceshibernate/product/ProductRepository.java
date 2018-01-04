package com.io.invoices.invoiceshibernate.product;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer>{
    public List<Product> findProductByUseryId(Integer useryId);
}
