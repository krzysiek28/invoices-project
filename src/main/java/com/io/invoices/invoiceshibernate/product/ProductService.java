package com.io.invoices.invoiceshibernate.product;


import com.io.invoices.invoiceshibernate.firm.FirmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    FirmRepository firmRepository;

    public void addProduct(Integer firmId, Product product) {
        if (!firmRepository.exists(firmId)) {
            throw new IllegalArgumentException("Bad company id!");
        }

        product.setOwner(firmRepository.findOne(firmId));
        productRepository.save(product);
    }

    public void updateProduct(Integer productId, Product product) {
        if (!productRepository.exists(productId)) {
            throw new IllegalArgumentException("Product does not exist!");
        }

        Product dbProduct = productRepository.findOne(productId);
        dbProduct.setName(product.getName());
        dbProduct.setNetUnitPrice(product.getNetUnitPrice());
        dbProduct.setVatRate(product.getVatRate());
        dbProduct.setUnit(product.getUnit());
        productRepository.save(dbProduct);
    }


    public List<Product> getProducts(int ownerId) {
        return productRepository.findProductByOwnerId(ownerId);
    }

    public void deleteProduct(Integer productId) {
        productRepository.findOne(productId).setOwner(null);
        productRepository.delete(productId);
    }
}
