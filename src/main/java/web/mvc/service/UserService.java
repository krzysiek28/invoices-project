package web.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import web.mvc.domain.Usery;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    public void login(String username, String password) throws URISyntaxException {

        URI uri = new URI("http://localhost:8090/login");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"username\": \""+username+"\",\"password\": \""+password+"\"}";
        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( uri, request, String.class);
        String key = response.getHeaders().get("Authorization").get(0).toString();
        userAuthenticationService.setToken(key);
    }

    public void logout() {
        userAuthenticationService.logout();
    }

    public void register(String email, String username, String password) throws URISyntaxException {

        URI uri = new URI("http://localhost:8090/users/sign-up");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson =
                "{" +
                "\"email\": \""+email+"\"," +
                "\"enabled\": \"true\"," +
                "\"username\": \""+username+"\"," +
                "\"password\": \""+password+"\"," +
                "\"role\":\"USER_ROLE\"}";
        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( uri, request, String.class);
        String key = response.getHeaders().get("Authorization").toString();
        System.out.println(key);
    }
/*
    public void getUserId(){
        URI uri = new URI("http://localhost:8090/login");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"username\": \""+username+"\",\"password\": \""+password+"\"}";
        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( uri, request, String.class);
        String key = response.getHeaders().get("Authorization").get(0).toString();
    }
*/
}