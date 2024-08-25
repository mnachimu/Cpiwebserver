package com.orisinterview.cpiwebserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BLSApiRequestBody {
    private String[] seriesid;
    private String startyear;
    private String endyear;
}
