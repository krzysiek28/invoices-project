package web.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class FirmService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    public void getFirms(){
        // dodać token od autoryzacji
        /*
        URI uri = new URI("http://localhost:8090/firms/"++);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"username\": \""+username+"\",\"password\": \""+password+"\"}";
        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( uri, request, String.class);
        String key = response.getHeaders().get("Authorization").get(0).toString();
        userAuthenticationService.setToken(key);
        */
    }

}
