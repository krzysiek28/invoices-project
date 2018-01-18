package com.io.invoices.invoiceshibernate.product;


import com.io.invoices.invoiceshibernate.firm.FirmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductController is controller which is responsible for
 * handle all requests product related
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;
    private final FirmService firmService;

    /**
     *
     * @param productService
     * @param firmService
     */
    public ProductController(ProductService productService, FirmService firmService) {
        this.productService = productService;
        this.firmService = firmService;
    }

    /**
     * method finds and returns list of products
     * company specified by id
     * @param firmId
     * @return
     */
    @RequestMapping("/{firmId}")
    public List<Product> getProducts(@PathVariable String firmId) {
        return productService.getProducts(Integer.parseInt(firmId));
    }

    /**
     * method add Product
     * to products list
     * which belongs to certain company
     * @param product
     * @param firmId
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{firmId}")
    public void addProduct(@RequestBody Product product, @PathVariable String firmId) {
        productService.addProduct(Integer.parseInt(firmId), product);
    }

    /**
     * method deletes product by id
     * which belongs to certain company with
     * specified companyId
     * @param productId
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{firmId}/{productId}")
    public void deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(Integer.parseInt(productId));
    }

    /**
     * method updates product with productId
     * @param product
     * @param productId
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{firmId}/{productId}")
    public void updateProduct(@RequestBody Product product, @PathVariable String productId) {
        productService.updateProduct(Integer.parseInt(productId), product);
    }

    /**
     * method finds and returns product with
     * param productId
     * @param productId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{firmId}/{productId}")
    public Product getProduct(@PathVariable String productId) {
        return productService.getProduct(Integer.parseInt(productId));
    }

}
