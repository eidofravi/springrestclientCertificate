package com.rest;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class _1GetEmployeeEnableSSL
{
    public static void main( String[] args )
    {
        final String uri = "https://localhost:8443/";
        HttpComponentsClientHttpRequestFactory factory = validateSSL();

        RestTemplate restTemplate = new RestTemplate(factory);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        System.out.println(result);
        System.out.println();
        System.out.println(result.getBody());
    }

    private static HttpComponentsClientHttpRequestFactory validateSSL(){
        String location = "C:\\Users\\ravi123\\IdeaProjects\\springrestclientCertificate\\certificate\\client.truststore";
        String pass = "raviclientpwd";
        SSLContext sslContext = null;
        try{
            sslContext = SSLContextBuilder
                    .create()
                    .loadTrustMaterial(ResourceUtils.getFile(location), pass.toCharArray())
                    .build();
        }catch (Exception e){

        }
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,new LocalHostnameVerifier());
        //SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return requestFactory;
    }

    private static class LocalHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return "localhost".equalsIgnoreCase(s) || "127.0.0.1".equals(s);
        }
    }
}
