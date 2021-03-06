package web.mvc.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import web.mvc.domain.Client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Service
public class ClientService {
    @Autowired
    private RestTemplate restTemplateHCCHRF;
    @Autowired
    UserAuthenticationService userAuthenticationService;


    public List<Client> getFirmClients() throws URISyntaxException, IOException, HttpClientErrorException {
        Integer companyId = userAuthenticationService.getFirmId();
        URI uri = new URI("http://localhost:8090/clients/"+companyId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        HttpEntity<String> entity = new HttpEntity<String>(headers);


        ResponseEntity<String> data = restTemplateHCCHRF.exchange(uri, HttpMethod.GET,entity,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(data.getBody(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Client.class));
    }

    public void addClient(String name, String additionalData) throws URISyntaxException, JSONException, HttpClientErrorException {

        Integer companyId = userAuthenticationService.getFirmId();
        URI uri = new URI("http://localhost:8090/clients/"+companyId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        String clientData = new JSONObject().put("name",name).put("additionalData",additionalData).toString();
        HttpEntity<String> request = new HttpEntity<String>(clientData, headers);
        ResponseEntity<String> response = restTemplateHCCHRF.exchange(uri, HttpMethod.POST, request, String.class);
    }

    public void updateClient(int id, String name, String additionalData) throws URISyntaxException, JSONException, HttpClientErrorException {
        Integer companyId = userAuthenticationService.getFirmId();
        URI uri = new URI("http://localhost:8090/clients/"+companyId+"/"+id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        String clientData = new JSONObject().put("name",name).put("additionalData",additionalData).toString();
        HttpEntity<String> request = new HttpEntity<String>(clientData, headers);
        ResponseEntity<String> response = restTemplateHCCHRF.exchange(uri, HttpMethod.PUT, request, String.class);
    }

    public void deleteClient(int clientId) throws URISyntaxException, HttpClientErrorException {
        Integer companyId = userAuthenticationService.getFirmId();
        URI uri = new URI("http://localhost:8090/clients/"+companyId+"/"+clientId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        restTemplateHCCHRF.exchange(uri,HttpMethod.DELETE,request,String.class);
    }
}
