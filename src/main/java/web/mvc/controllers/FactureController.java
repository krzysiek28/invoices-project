package web.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

import static java.nio.charset.StandardCharsets.*;

@Controller
public class FactureController {

    @RequestMapping(value = "/createfacture/step2", method = RequestMethod.POST)
    public String step2(@RequestParam("clientid") String clientId,
                      @RequestParam("clientname") String clientName,
                      @RequestParam("clientdata") String clientData,
                      @RequestParam("accountnumber") String accountNumber,
                      @RequestParam("accountdata") String accountData,
                      @RequestParam("invoicenumber") String number,
                      @RequestParam("invoiceplace") String place,
                      @RequestParam("invoiceissuedate") String issueDate,
                      @RequestParam("invoicepaymentdate") String paymentDate) throws UnsupportedEncodingException {

        return "ok";
    }
}
