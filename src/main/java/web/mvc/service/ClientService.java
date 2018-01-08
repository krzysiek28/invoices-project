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


    public List<Client> getFirmClients() throws URISyntaxException, IOException {
        Integer companyId = 24; //get from userauthentivationserbice
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

    public void addClient(String name, String additionalData) throws URISyntaxException, JSONException {

        Integer companyId = 24;
        URI uri = new URI("http://localhost:8090/clients/"+companyId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        String clientData = new JSONObject().put("name",name).put("additionalData",additionalData).toString();
        System.out.println(clientData);
        HttpEntity<String> request = new HttpEntity<String>(clientData, headers);
        ResponseEntity<Component> response = restTemplateHCCHRF.exchange(uri, HttpMethod.POST, request, Component.class);
    }
}
