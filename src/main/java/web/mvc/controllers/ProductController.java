package web.mvc.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import web.mvc.domain.Client;
import web.mvc.domain.Product;
import web.mvc.service.ProductService;
import web.mvc.service.UserAuthenticationService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ProductController {

    private final UserAuthenticationService userAuthenticationService;
    private final ProductService productService;

    public ProductController(UserAuthenticationService userAuthenticationService,
                             ProductService productService) {
        this.userAuthenticationService = userAuthenticationService;
        this.productService = productService;
    }

    @RequestMapping(value = "/products")
    public String productsPage(HttpServletRequest request,
                               ModelMap modelMap) throws IOException, URISyntaxException, JSONException {
        try {
            modelMap.addAttribute("authservice", userAuthenticationService);
            List<Product> firmProducts = productService.getFirmProducts();
            Collections.sort(firmProducts, Comparator.comparingInt(Product::getId));
            modelMap.addAttribute("products", firmProducts);
            return "products";
        } catch (HttpClientErrorException e) {
            JSONObject obj = new JSONObject(e.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/products?error=" + errorMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }

    @RequestMapping(value = "/products/addproduct", method = RequestMethod.POST)
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("netUnitPrice") Float netUnitPrice,
                             @RequestParam("unit") String unit,
                             @RequestParam("vatRate") Float vatRate,
                             @RequestParam("currency") String currency,
                             HttpServletRequest request,
                             ModelMap modelMap) throws JSONException {
        try {
            productService.addProduct(name, netUnitPrice, unit, vatRate/100, currency);
        } catch (HttpClientErrorException e) {
            JSONObject obj = new JSONObject(e.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/products?error=" + errorMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }

    @RequestMapping(value = "/products/deleteproduct/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") String id) throws JSONException {
        try {
            productService.deleteProductById(Integer.parseInt(id));
        } catch (HttpClientErrorException e) {
            JSONObject obj = new JSONObject(e.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/products?error=" + errorMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }

    @RequestMapping(value = "/products/updateproduct/{id}", method = RequestMethod.POST)
    public String updateProduct(@RequestParam("name") String name,
                             @RequestParam("netUnitPrice") Float netUnitPrice,
                             @RequestParam("unit") String unit,
                             @RequestParam("vatRate") Float vatRate,
                                @RequestParam("currency") String currency,
                             @PathVariable("id") String id,
                             HttpServletRequest request,
                             ModelMap modelMap) throws URISyntaxException, JSONException {
        try {

            productService.updateProduct(Integer.parseInt(id), name, netUnitPrice, unit, vatRate/100, currency);
        } catch (HttpClientErrorException e) {
            JSONObject obj = new JSONObject(e.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/products?error=" + errorMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }


}
