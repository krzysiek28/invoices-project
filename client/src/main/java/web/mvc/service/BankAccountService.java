package web.mvc.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import web.mvc.domain.BankAccount;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private RestTemplate restTemplateHCCHRF;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    public List<BankAccount> getBankAccounts()throws URISyntaxException, IOException, HttpClientErrorException {
        Integer companyId = userAuthenticationService.getFirmId();
        URI uri = new URI("http://localhost:8090/bankaccounts/"+companyId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        HttpEntity<String> entity = new HttpEntity<String>(headers);


        ResponseEntity<String> data = restTemplateHCCHRF.exchange(uri, HttpMethod.GET,entity,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(data.getBody(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, BankAccount.class));
    }

    public void addBankAccount(String bankAccount, String additionalData) throws URISyntaxException, IOException, HttpClientErrorException, JSONException {
        Integer companyId = userAuthenticationService.getFirmId();
        URI uri = new URI("http://localhost:8090/bankaccounts/"+companyId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        String bankData = new JSONObject().put("bankAccount",bankAccount).put("additionalData",additionalData).toString();
        HttpEntity<String> request = new HttpEntity<String>(bankData, headers);
        ResponseEntity<String> response = restTemplateHCCHRF.exchange(uri, HttpMethod.POST, request, String.class);
    }

    public void updateBankAccount(String additionalData, String bankAccountId) throws URISyntaxException, IOException, HttpClientErrorException, JSONException {
        Integer companyId = userAuthenticationService.getFirmId();
        URI uri = new URI("http://localhost:8090/bankaccounts/"+companyId+"/"+bankAccountId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        String bankData = new JSONObject().put("bankAccount",bankAccountId).put("additionalData",additionalData).toString();
        HttpEntity<String> request = new HttpEntity<String>(bankData, headers);
        ResponseEntity<String> response = restTemplateHCCHRF.exchange(uri, HttpMethod.PUT, request, String.class);
    }

    public void deleteBankAccount(String bankAccountId) throws URISyntaxException, IOException, HttpClientErrorException{
        Integer companyId = userAuthenticationService.getFirmId();
        URI uri = new URI("http://localhost:8090/bankaccounts/"+companyId+"/"+bankAccountId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        restTemplateHCCHRF.exchange(uri,HttpMethod.DELETE,request,String.class);
    }

}
