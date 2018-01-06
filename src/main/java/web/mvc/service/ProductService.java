package web.mvc.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import web.mvc.domain.Client;
import web.mvc.domain.Product;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getOwnerId(String userId){
        String sqlUser = String.format("SELECT id FROM usery WHERE email = '%s'", userId);
        return jdbcTemplate.queryForObject(sqlUser, String.class);
    }

    public List<Product> getProductsByOwnerID(String userId) throws URISyntaxException, IOException {
        String sqlUser = String.format("SELECT id FROM usery WHERE email = '%s'", userId);
        String id = jdbcTemplate.queryForObject(sqlUser, String.class);
        URI uri = new URI("http://localhost:8090/products/"+id);
        String data = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(data,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));
    }

    public Map<String, String> addProduct(String name, Float netUnitPrice, String unit, Float vatRate, String usery) throws URISyntaxException {
        URI uri = new URI("http://localhost:8090/products/addproduct");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name", name);
        map.add("netUnitPrice", String.valueOf(netUnitPrice));
        map.add("unit", unit);
        map.add("vatRate", String.valueOf(vatRate));
        map.add("usery", usery);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
        map.add("response", response.getBody());
        return map.toSingleValueMap();
    }

    public void deleteProductById(String id) throws URISyntaxException {
        URI uri = new URI("http://localhost:8090/products/deleteproduct/"+id);
        restTemplate.getForObject(uri, String.class);
    }
}
