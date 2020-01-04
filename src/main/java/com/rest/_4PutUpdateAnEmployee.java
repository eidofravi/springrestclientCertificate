package com.rest;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class _4PutUpdateAnEmployee {
    public static void main(String[] args) {
        final String uri = "http://localhost:8081/api/employee/111";
        final String payload = "{\"firstName\":\"supriyakhandelwal\",\"employeeId\":444}";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(payload, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class);

        System.out.println(result);
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());
    }
}
