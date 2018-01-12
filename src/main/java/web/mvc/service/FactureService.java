package web.mvc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import web.mvc.domain.Client;
import web.mvc.domain.Facture;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class FactureService {

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    RestTemplate restTemplateHCCHRF;

    public List<Facture> getAllFactures() throws URISyntaxException, IOException {
        Integer companyId = userAuthenticationService.getFirmId();
        URI uri = new URI("http://localhost:8090/factures/"+companyId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> data = restTemplateHCCHRF.exchange(uri, HttpMethod.GET,entity,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(data.getBody(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Facture.class));
    }

    public Facture getFacture(int factureId) throws URISyntaxException, IOException {
        Integer companyId = userAuthenticationService.getFirmId();
        URI uri = new URI("http://localhost:8090/factures/"+companyId+"/"+factureId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> data = restTemplateHCCHRF.exchange(uri, HttpMethod.GET,entity,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(data.getBody(),
                objectMapper.getTypeFactory().constructType(Facture.class));
    }

    public void addFacture(Facture facture) throws JsonProcessingException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        String factureString = mapper.writeValueAsString(facture);
        Integer companyId = userAuthenticationService.getFirmId();
        URI uri = new URI("http://localhost:8090/factures/"+companyId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        HttpEntity<String> request = new HttpEntity<String>(factureString, headers);
        ResponseEntity<String> response = restTemplateHCCHRF.exchange(uri, HttpMethod.POST, request, String.class);
    }
}






