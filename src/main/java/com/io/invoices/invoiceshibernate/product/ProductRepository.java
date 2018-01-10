package com.io.invoices.invoiceshibernate.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductByOwnerId(Integer ownerId);

    @Transactional
    long deleteProductsByOwnerId(Integer ownerId);

}
