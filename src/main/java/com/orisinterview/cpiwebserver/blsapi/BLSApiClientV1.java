package com.orisinterview.cpiwebserver.blsapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orisinterview.cpiwebserver.Outputtest;
import com.orisinterview.cpiwebserver.models.BLSApiRequestBody;
import com.orisinterview.cpiwebserver.models.BLSApiResponseBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

public class BLSApiClientV1 extends BLSApiClient {
    @Override
    BLSApiResponseBody getBlsDataForYear(String seriesID, String year) {
        final String uri = "https://api.bls.gov/publicAPI/v1/timeseries/data/";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        BLSApiRequestBody body = new BLSApiRequestBody(
                new String[]{seriesID},
                addYear(year, -1),
                addYear(year, 1));
        HttpEntity<BLSApiRequestBody> entity = new HttpEntity<>(body, headers);
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

    private String addYear(String year, int val) {
        return String.valueOf(Integer.valueOf(year) + val);
    }
}
