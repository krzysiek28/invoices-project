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

    public void addProduct(Integer userId, Product product) {
        if (userRepository.exists(userId)) {
            throw new IllegalArgumentException("Bad product owner id!");
        }

        product.setUsery(userRepository.findOne(userId));
        productRepository.save(product);
    }


    public List<Product> getProducts(int userId) {
        return productRepository.findProductByUseryId(userId);
    }

    public void deleteProduct(Integer productId) {
        productRepository.delete(productId);
    }
}
