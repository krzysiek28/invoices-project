package web.mvc.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
    private RestTemplate restTemplate;

    public List<Client> getClients() throws URISyntaxException, IOException {
        URI uri = new URI("http://localhost:8090/clients/");
        String data = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(data);
        //objectMapper.writeValueAsString(data);
        return objectMapper.readValue(data,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Client.class));
    }

    public Map<String, String> addClient(String name, String additionalData/*, Usery owner*/) throws URISyntaxException {
        URI uri = new URI("http://localhost:8090/clients/addclient");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name", name);
        map.add("additionalData", additionalData);
        //map.add("owner", String.valueOf(owner));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
        map.add("response", response.getBody());
        return map.toSingleValueMap();
    }

}
