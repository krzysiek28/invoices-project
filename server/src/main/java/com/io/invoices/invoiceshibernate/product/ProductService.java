package com.io.invoices.invoiceshibernate.product;


import com.io.invoices.invoiceshibernate.firm.FirmRepository;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final FirmRepository firmRepository;

    public ProductService(ProductRepository productRepository, FirmRepository firmRepository) {
        this.productRepository = productRepository;
        this.firmRepository = firmRepository;
    }

    public void addProduct(Integer firmId, Product product) {
        if (!firmRepository.exists(firmId)) {
            throw new IllegalArgumentException("Niepoprawne id firmy.");
        }

        product.stipTags();
        if (!product.isCorrect()) {
            throw new IllegalArgumentException("Podano niepoprawne dane!");
        }


        product.setOwner(firmRepository.findOne(firmId));
        productRepository.save(product);
    }

    public void updateProduct(Integer productId, Product product) {
        if (!productRepository.exists(productId)) {
            throw new IllegalArgumentException("Produkt/usługa nie istnieje!");
        }

        product.stipTags();
        if (!product.isCorrect()) {
            throw new IllegalArgumentException("Podano niepoprawne dane!");
        }

        Product dbProduct = productRepository.findOne(productId);
        dbProduct.setName(product.getName());
        dbProduct.setNetUnitPrice(product.getNetUnitPrice());
        dbProduct.setVatRate(product.getVatRate());
        dbProduct.setUnit(product.getUnit());
        dbProduct.setCurrency(product.getCurrency());
        productRepository.save(dbProduct);
    }


    public List<Product> getProducts(int ownerId) {
        if (!firmRepository.exists(ownerId))
            throw new IllegalArgumentException("Niepoprawne id firmy.");
        return productRepository.findProductByOwnerId(ownerId);
    }

    public void deleteProduct(Integer productId) {
        if (!productRepository.exists(productId))
            throw new IllegalArgumentException("Produkt/usługa nie istnieje!");

        productRepository.findOne(productId).setOwner(null);
        productRepository.delete(productId);
    }

    public Product getProduct(int i) {
        if (!productRepository.exists(i))
            throw new IllegalArgumentException("Produkt/usługa nie istnieje!");
        return productRepository.findOne(i);
    }
}
