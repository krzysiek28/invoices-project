package com.io.invoices.invoiceshibernate.product;


import com.io.invoices.invoiceshibernate.firm.FirmService;
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
    FirmService firmService;

    @RequestMapping("/{firmId}")
    public List<Product> getProducts(@PathVariable String firmId) {
        return productService.getProducts(Integer.parseInt(firmId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{firmId}")
    public void addProduct(@RequestBody Product product, @PathVariable String firmId) {
        productService.addProduct(Integer.parseInt(firmId), product);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{firmId}/{productId}")
    public void deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(Integer.parseInt(productId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{firmId}/{productId}")
    public void updateProduct(@RequestBody Product product, @PathVariable String productId) {
        productService.updateProduct(Integer.parseInt(productId), product);
    }

}
