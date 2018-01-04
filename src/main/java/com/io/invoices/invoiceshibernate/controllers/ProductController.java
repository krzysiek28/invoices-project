package com.io.invoices.invoiceshibernate.controllers;


import com.io.invoices.invoiceshibernate.product.Product;
import com.io.invoices.invoiceshibernate.product.ProductService;
import com.io.invoices.invoiceshibernate.user.Usery;
import com.io.invoices.invoiceshibernate.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @RequestMapping("/{username}/products")
    public List<Product> getProducts(@PathVariable String username) {
        return productService.getProducts(username);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{username}/products")
    public void addBankAccount(@RequestBody Product product, @PathVariable String username) {
        //todo usery username to authorize
        Usery usery = userService.getUser(username);
        product.setUsery(usery);
        productService.addProduct(product);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{username}/products/{productId}")
    public void deleteAccount(@PathVariable String productId) {

        productService.deleteProduct(productId);
    }

}
