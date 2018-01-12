package com.io.invoices.invoiceshibernate.product;


import com.io.invoices.invoiceshibernate.firm.FirmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;
    private final FirmService firmService;

    public ProductController(ProductService productService, FirmService firmService) {
        this.productService = productService;
        this.firmService = firmService;
    }

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

    @RequestMapping(method = RequestMethod.GET, value = "/{firmId}/{productId}")
    public Product getProduct(@PathVariable String productId) {
        return productService.getProduct(Integer.parseInt(productId));
    }

}
