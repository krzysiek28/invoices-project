package web.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class FirmService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    public void getFirms() throws URISyntaxException {

        URI uri = new URI("http://localhost:8090/firms/"+userAuthenticationService.getUserId());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

    }

}
