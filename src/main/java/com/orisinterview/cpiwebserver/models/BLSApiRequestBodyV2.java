package com.orisinterview.cpiwebserver.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class BLSApiRequestBodyV2 extends BLSApiRequestBodyV1 {
    @JsonProperty(defaultValue = "false")
    private boolean catalog;
    @JsonProperty(defaultValue = "false")
    private boolean calculations;
    @JsonProperty(defaultValue = "false")
    private boolean annualaverage;
    @JsonProperty(defaultValue = "false")
    private boolean aspects;
    @JsonProperty("registrationkey")
    private String registrationKey;
    public BLSApiRequestBodyV2(String[] seriesid, String startyear, String endyear) {
        super(seriesid, startyear, endyear);
    }

}
