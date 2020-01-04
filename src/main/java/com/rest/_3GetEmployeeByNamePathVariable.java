package com.rest;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class _3GetEmployeeByNamePathVariable
{
    public static void main( String[] args )
    {
        final String uri = "http://localhost:8081/api/employee/ravi";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        System.out.println(result);
    }
}
