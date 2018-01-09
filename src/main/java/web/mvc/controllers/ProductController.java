package web.mvc.controllers;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.mvc.service.ProductService;
import web.mvc.service.UserAuthenticationService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;

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
                               ModelMap modelMap) throws IOException, URISyntaxException {
        modelMap.addAttribute("authservice", userAuthenticationService);
        modelMap.addAttribute("products", productService.getFirmProducts());
        return "products";
    }

    @RequestMapping(value = "/products/addproduct", method = RequestMethod.POST)
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("netUnitPrice") Float netUnitPrice,
                             @RequestParam("unit") String unit,
                             @RequestParam("vatRate") Float vatRate,
                             HttpServletRequest request,
                             ModelMap modelMap) {
        try {
            productService.addProduct(name, netUnitPrice, unit, vatRate/100);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }
//
//    @RequestMapping(value = "/products/deleteproduct/{id}", method = RequestMethod.GET)
//    public String deleteProduct(@PathVariable("id") String id) {
//        try {
//            productService.deleteProductById(id);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        return "redirect:/products";
//    }


}
