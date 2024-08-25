package com.orisinterview.cpiwebserver.blsapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orisinterview.cpiwebserver.models.BLSApiRequestBodyV1;
import com.orisinterview.cpiwebserver.models.BLSApiRequestBodyV2;
import com.orisinterview.cpiwebserver.models.BLSApiResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BLSApiClientV2 extends BLSApiClient {

    @Value("${spring.application.blsapi.registrationkey}")
    String registrationKey;
    @Value("${spring.application.blsapi.v2}")
    String uri;
    @Override
    BLSApiResponseBody getBlsDataForYear(String seriesID, String year) {
        System.out.println("Using v2 api...");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        BLSApiRequestBodyV2 body = new BLSApiRequestBodyV2(
                new String[]{seriesID},
                addYear(year, -1),
                addYear(year, 1));
        body.setRegistrationKey(registrationKey);
        HttpEntity<BLSApiRequestBodyV1> entity = new HttpEntity<>(body, headers);
        ResponseEntity responseEntity = null;
        BLSApiResponseBody res = null;
        try {
            responseEntity = restTemplate.exchange(uri, HttpMethod.POST,
                    entity, String.class);

            if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                System.out.println("Not successful...");
            }
            System.out.println(responseEntity.getStatusCode());
            String r = (String) responseEntity.getBody();
            System.out.println(r);
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(r, BLSApiResponseBody.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
