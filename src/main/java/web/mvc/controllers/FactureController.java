package web.mvc.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.mvc.domain.Facture;
import web.mvc.domain.Product;
import web.mvc.domain.ProductEntry;
import web.mvc.service.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class FactureController {

    @Autowired
    FactureService factureService;

    @Autowired
    FactureBuilderService factureBuilderService;

    @Autowired
    ProductService productService;

    @Autowired
    ClientService clientService;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    BankAccountService bankAccountService;

    @RequestMapping(value = "/createfacture")
    public String createf(ModelMap modelMap) {
        try {
            factureBuilderService.newFacture();
            modelMap.addAttribute("clients", clientService.getFirmClients());
            modelMap.addAttribute("authservice", userAuthenticationService);
            modelMap.addAttribute("accounts", bankAccountService.getBankAccounts());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "createfacture";
    }

    @RequestMapping(value = "/createfacture/step1", method = RequestMethod.POST)
    public String step1(@RequestParam("clientid") String clientId,
                        @RequestParam("clientname") String clientName,
                        @RequestParam("clientdata") String clientData,
                        @RequestParam("accountnumber") String accountNumber,
                        @RequestParam("accountdata") String accountData,
                        @RequestParam("invoicenumber") String number,
                        @RequestParam("invoiceplace") String place,
                        @RequestParam("invoiceissuedate") String issueDate,
                        @RequestParam("invoicepaymentdate") String paymentDate,
                        @RequestParam("currency") String currency) throws Exception {
        //todo: clientid = 1
        if (clientId.equals("-1"))
            throw new Exception("unimplemented");

        factureBuilderService.setClientData(clientId);
        factureBuilderService.setBankAccount(accountNumber);
        factureBuilderService.setFactureData(number, place);
        factureBuilderService.setDates(issueDate, paymentDate);
        factureBuilderService.setCurrency(currency);

        return "redirect:/createfacture2";
    }

    @RequestMapping(value = "/createfacture2")
    public String step2(ModelMap modelMap) throws Exception {
        List<Product> firmProducts = productService.getFirmProducts();
        Collections.sort(firmProducts, Comparator.comparingInt(Product::getId));
        modelMap.addAttribute("products", firmProducts);
        return "createfacture2";
    }

    @RequestMapping(value = "/createfacture3")
    public String step3(ModelMap modelMap) throws Exception {
        factureBuilderService.calculatePrices();
        modelMap.addAttribute("facture", factureBuilderService.getFacture());
        return "createfacture3";
    }

    @RequestMapping(value = "/createfacture/addproduct", method = RequestMethod.POST)
    public ResponseEntity addProduct(@RequestParam String productId,
                                     @RequestParam String quantity) {
        factureBuilderService.addProduct(Integer.parseInt(productId), Float.parseFloat(quantity));
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/createfacture/deleteproduct", method = RequestMethod.POST)
    public ResponseEntity addProduct(@RequestParam String productId) {
        factureBuilderService.deleteProduct(Integer.parseInt(productId));
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/createfacture/add", method = RequestMethod.POST)
    public String sendFacture(@RequestParam String paymentmethod,
                              @RequestParam String paid) {
        //todo: clientid = 1
        try {
            Facture facture = factureBuilderService.getFacture();
            facture.setPaymentMethod(paymentmethod);
            facture.setPaid(Float.parseFloat(paid));
            facture.setToPay(facture.getTotal() - facture.getPaid());

            List<ProductEntry> products = factureBuilderService.getFacture().getProducts();
            for (int i = 0; i < products.size(); i++)
                products.get(i).setNo(i + 1);
            factureService.addFacture(factureBuilderService.getFacture());
            factureBuilderService.newFacture();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return "redirect:/facturesList";
    }

    @RequestMapping(value = "/facture/{id}", method = RequestMethod.GET)
    public String showFacture(@PathVariable String id, ModelMap modelMap) {
        try {
            Facture facture = factureService.getFacture(Integer.parseInt(id));
            modelMap.addAttribute("facture", facture);
            modelMap.addAttribute("client", facture.getClient());
            modelMap.addAttribute("firm", facture.getFirm());
            modelMap.addAttribute("bankaccount", facture.getBankAccount());
            modelMap.addAttribute("productentries", facture.getProducts());


        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "facture";
    }

    @RequestMapping(value = "/facturesList")
    public String showFactureList(ModelMap modelMap) {
        try {
            List<Facture> allFactures = factureService.getAllFactures();
            modelMap.addAttribute("factures", allFactures);

        } catch (URISyntaxException e) {

        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        }


        return "facturesList";
    }
}
