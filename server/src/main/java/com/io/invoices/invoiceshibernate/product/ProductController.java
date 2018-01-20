package com.io.invoices.invoiceshibernate.product;


import com.io.invoices.invoiceshibernate.firm.FirmService;
import com.io.invoices.invoiceshibernate.security.AuthorizationFilter;
import com.io.invoices.invoiceshibernate.security.ResourceType;
import com.io.invoices.invoiceshibernate.security.UnauthorizedException;
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
    private final AuthorizationFilter authorizationFilter;

    /**
     *  @param productService
     * @param firmService
     * @param authorizationFilter
     */
    public ProductController(ProductService productService, FirmService firmService, AuthorizationFilter authorizationFilter) {
        this.productService = productService;
        this.firmService = firmService;
        this.authorizationFilter = authorizationFilter;
    }

    /**
     * method finds and returns list of products
     * company specified by id
     * @param firmId
     * @return
     */
    @RequestMapping("/{firmId}")
    public List<Product> getProducts(@PathVariable String firmId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,firmId, ResourceType.FIRM);
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
    public void addProduct(@RequestBody Product product, @PathVariable String firmId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,firmId,ResourceType.FIRM);
        productService.addProduct(Integer.parseInt(firmId), product);
    }

    /**
     * method deletes product by id
     * which belongs to certain company with
     * specified companyId
     * @param productId
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{firmId}/{productId}")
    public void deleteProduct(@PathVariable String productId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,productId,ResourceType.PRODUCT);
        productService.deleteProduct(Integer.parseInt(productId));
    }

    /**
     * method updates product with productId
     * @param product
     * @param productId
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{firmId}/{productId}")
    public void updateProduct(@RequestBody Product product, @PathVariable String productId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,productId,ResourceType.PRODUCT);
        productService.updateProduct(Integer.parseInt(productId), product);
    }


    /**
     * method finds and returns product with
     * param productId
     * @param productId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{firmId}/{productId}")
    public Product getProduct(@PathVariable String productId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,productId,ResourceType.PRODUCT);
        return productService.getProduct(Integer.parseInt(productId));
    }

}
