package web.mvc.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import web.mvc.domain.Firm;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class FirmService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    public List<Firm> getFirms() throws URISyntaxException, JSONException, IOException {

        URI uri = new URI("http://localhost:8090/firms/"+userAuthenticationService.getUserId());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        //JSONArray obj = new JSONArray(response.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(response.getBody(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Firm.class));
    }

    public void addFirm(String email, String name, String nip, String phone, String place) throws URISyntaxException, JSONException {

        URI uri = new URI("http://localhost:8090/firms/"+userAuthenticationService.getUserId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email).put("name", name).put("nip", nip)
                .put("phone", phone).put("place", place).put("owner_id", userAuthenticationService.getUserId());
        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<Component> response = restTemplate.exchange(uri, HttpMethod.POST, request, Component.class);
    }
}
