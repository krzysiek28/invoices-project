package web.mvc.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import web.mvc.domain.Client;
import web.mvc.domain.Usery;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Service
public class ClientService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getOwnerId(String userId){
        String sqlUser = String.format("SELECT id FROM usery WHERE email = '%s'", userId);
        return jdbcTemplate.queryForObject(sqlUser, String.class);
    }

    public List<Client> getClientsByOwnerID(String userId) throws URISyntaxException, IOException {
        String sqlUser = String.format("SELECT id FROM usery WHERE email = '%s'", userId);
        String id = jdbcTemplate.queryForObject(sqlUser, String.class);
        URI uri = new URI("http://localhost:8090/clients/"+id);
        String data = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        System.out.println(data);
        return objectMapper.readValue(data,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Client.class));
    }

    public Map<String, String> addClient(String name, String additionalData, String owner) throws URISyntaxException {
        URI uri = new URI("http://localhost:8090/clients/addclient");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name", name);
        map.add("additionalData", additionalData);
        map.add("owner", owner);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
        map.add("response", response.getBody());
        return map.toSingleValueMap();
    }

    public void deleteClientById(String id) throws URISyntaxException {
        URI uri = new URI("http://localhost:8090/clients/deleteclient/"+id);
        restTemplate.getForObject(uri, String.class);
    }
}
