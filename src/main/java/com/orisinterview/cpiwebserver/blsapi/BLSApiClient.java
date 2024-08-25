package com.orisinterview.cpiwebserver.blsapi;

import com.orisinterview.cpiwebserver.models.BLSApiResponseBody;

public abstract class BLSApiClient {

    private static final String DEFAULT_CHAINED_CPI_ALL_URBAN_CONSUMERS = "SUUR0000SA0";

    abstract BLSApiResponseBody getBlsDataForYear(String seriesID, String year);

    public BLSApiResponseBody getBlsCPIDataForYear(String year) {
        return this.getBlsDataForYear(DEFAULT_CHAINED_CPI_ALL_URBAN_CONSUMERS, year);
    }

    protected String addYear(String year, int val) {
        return String.valueOf(Integer.valueOf(year) + val);
    }
}
