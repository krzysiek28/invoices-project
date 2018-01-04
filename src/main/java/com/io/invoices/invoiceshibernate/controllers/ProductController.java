package com.io.invoices.invoiceshibernate.controllers;


import com.io.invoices.invoiceshibernate.product.Product;
import com.io.invoices.invoiceshibernate.product.ProductService;
import com.io.invoices.invoiceshibernate.user.Usery;
import com.io.invoices.invoiceshibernate.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @RequestMapping("/{userId}")
    public List<Product> getProducts(@PathVariable String userId) {
        return productService.getProducts(Integer.parseInt(userId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{userId}")
    public void addProduct(@RequestBody Product product, @PathVariable String userId) {
        productService.addProduct(Integer.parseInt(userId), product);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}/{productId}")
    public void deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(Integer.parseInt(productId));
    }

}
