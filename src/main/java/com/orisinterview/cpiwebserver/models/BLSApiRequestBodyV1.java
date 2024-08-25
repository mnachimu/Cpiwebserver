package com.orisinterview.cpiwebserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BLSApiRequestBodyV1 {
    protected String[] seriesid;
    protected String startyear;
    protected String endyear;
}
