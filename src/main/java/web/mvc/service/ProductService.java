package web.mvc.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.mvc.domain.Client;
import web.mvc.domain.Product;
import web.mvc.domain.User;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class ProductService {

    final private RestTemplate restTemplateHCCHRF;
    final private UserAuthenticationService userAuthenticationService;

    public ProductService(UserAuthenticationService userAuthenticationService,
                          RestTemplate restTemplateHCCHRF) {
        this.userAuthenticationService = userAuthenticationService;
        this.restTemplateHCCHRF = restTemplateHCCHRF;
    }

    public List<Product> getFirmProducts() throws URISyntaxException, IOException {
        Integer companyId = 24; //get from userauthentivationserbice
        URI uri = new URI("http://localhost:8090/products/"+companyId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> data = restTemplateHCCHRF.exchange(uri, HttpMethod.GET,entity,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(data.getBody(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));
    }

    public void addProduct(String name, Float netUnitPrice, String unit, Float vatRate) throws JSONException, URISyntaxException {
        Integer companyId = 24;
        URI uri = new URI("http://localhost:8090/products/"+companyId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        String productData = new JSONObject()
                .put("name",name)
                .put("netUnitPrice",netUnitPrice)
                .put("unit", unit)
                .put("vatRate",vatRate)
                .toString();
        HttpEntity<String> request = new HttpEntity<String>(productData, headers);
        ResponseEntity<Component> response = restTemplateHCCHRF.exchange(uri, HttpMethod.POST, request, Component.class);
    }




/*
    public void getProducts(){
        URI uri = new URI("http://localhost:8090/products/");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"username\": \""+username+"\",\"password\": \""+password+"\"}";
        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( uri, request, String.class);
        String key = response.getHeaders().get("Authorization").get(0).toString();
        userAuthenticationService.setToken(key);
    }
    */
}
