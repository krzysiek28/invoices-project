package web.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class UserService {

    @Autowired
    UserAuthenticationService userAuthenticationService;
    @Autowired
    private RestTemplate restTemplateHCCHRF;

    public void login(String username, String password) throws URISyntaxException, HttpClientErrorException {

        URI uri = new URI("http://localhost:8090/login");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"username\": \"" + username + "\",\"password\": \"" + password + "\"}";
        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);

        ResponseEntity<Component> response = restTemplateHCCHRF.exchange(uri, HttpMethod.POST, request, Component.class);
        String key = response.getHeaders().get("Authorization").get(0).toString();
        System.out.println(response.getBody());
        userAuthenticationService.setToken(key);


    }

    public void logout() {
        userAuthenticationService.logout();
    }

    public void register(String email, String username, String password) throws URISyntaxException, HttpClientErrorException {

        URI uri = new URI("http://localhost:8090/users/sign-up");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson =
                "{" +
                        "\"email\": \"" + email + "\"," +
                        "\"enabled\": \"true\"," +
                        "\"username\": \"" + username + "\"," +
                        "\"password\": \"" + password + "\"," +
                        "\"role\":\"USER_ROLE\"}";
        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);

        ResponseEntity<Component> response = restTemplateHCCHRF.exchange(uri, HttpMethod.POST,request, Component.class);
        String key = response.getHeaders().get("Authorization").toString();
        userAuthenticationService.setToken(key);

    }
}