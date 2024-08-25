package com.orisinterview.cpiwebserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orisinterview.cpiwebserver.blsapi.BLSApiClient;
import com.orisinterview.cpiwebserver.cache.CacheManager;
import com.orisinterview.cpiwebserver.cache.ICacheManager;
import com.orisinterview.cpiwebserver.models.BLSApiResponseBody;
import com.orisinterview.cpiwebserver.models.Data;
import com.orisinterview.cpiwebserver.models.CpiResponseBody;
import com.orisinterview.cpiwebserver.models.Notes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class Executor {

    @Autowired
    BLSApiClient apiClient;

    @Autowired
    ICacheManager cacheManager;

    public CpiResponseBody getCPIForMonthAndYear(String month, String year) {
        CpiResponseBody resBody = new CpiResponseBody();
        if (cacheManager.containsQuiet(generateCacheKey(month, year))) {
            try {
                String cachedString = cacheManager.getQuiet(generateCacheKey(month, year));
                ObjectMapper objectMapper = new ObjectMapper();
                resBody = objectMapper.readValue(cachedString, CpiResponseBody.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resBody;
        }
        try {
            BLSApiResponseBody blsResponse = apiClient.getBlsCPIDataForYear(year);
            String seriesId = blsResponse.getResults().getSeries().get(0).getSeriesID();
            List<Data> data = blsResponse.getResults().getSeries().get(0).getData();
            int val;
            StringBuilder nb;
            for (Data d : data) {
                val = (int) Math.round(Double.valueOf(d.getValue()));
                nb = new StringBuilder();
                nb.append(System.lineSeparator());
                for (Notes n : d.getFootnotes())
                    nb.append(System.lineSeparator()).append(n.getText() == null ? "" : n.getText());
                if (d.getPeriodName().equals(month) && d.getYear().equals(year)) {
                    resBody.setCpiValue(val);
                    resBody.setNotes(nb.toString().trim());
                }
                ObjectMapper objectMapper = new ObjectMapper();
                cacheManager.put(
                        generateCacheKey(d.getPeriodName(), d.getYear()),
                        objectMapper.writeValueAsString(new CpiResponseBody(val, nb.toString().trim())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resBody;
    }

    private String generateCacheKey(String month, String year) {
        return month + "-" + year;
    }
}
