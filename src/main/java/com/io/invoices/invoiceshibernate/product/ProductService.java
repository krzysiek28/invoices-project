package com.io.invoices.invoiceshibernate.product;


import com.io.invoices.invoiceshibernate.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getProducts(String userName) {
        return productRepository.findProductByUseryName(userName);
    }

    public void deleteProduct(String productId) {
        productRepository.delete(productId);
    }
}
