package web.mvc.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import web.mvc.domain.Client;

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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import web.mvc.domain.Facture;

public class FactureService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Facture> getFacturesByOwnerID(String userId) throws URISyntaxException, IOException {
        String sqlUser = String.format("SELECT id FROM usery WHERE email = '%s'", userId);
        String id = jdbcTemplate.queryForObject(sqlUser, String.class);
        URI uri = new URI("http://localhost:8090/"+id+"/factures");
        String data = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        System.out.println(data);
        return objectMapper.readValue(data,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Facture.class));
    }
}
