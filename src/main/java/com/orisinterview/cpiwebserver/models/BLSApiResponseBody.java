package com.orisinterview.cpiwebserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonPOJOBuilder
@JsonDeserialize
@JsonIgnoreProperties
public class BLSApiResponseBody {
    @JsonProperty("status")
    private String status;
    @JsonProperty("responseTime")
    private int responseTime;
    @JsonProperty("message")
    private List<String> message;
    @JsonProperty("Results")
    private Result results;

}

//@AllArgsConstructor
//@Getter
//@Setter
//@JsonPOJOBuilder
//@JsonDeserialize
//public
//
//@AllArgsConstructor
//@Getter
//@Setter
//@JsonPOJOBuilder
//@JsonDeserialize
//public class Series {
//    private String seriesID;
//    private Data[] data;
//}
//
//@AllArgsConstructor
//@Getter
//@Setter
//@JsonPOJOBuilder
//@JsonDeserialize
//public class Data {
//    private String year;
//    private String period;
//    private String periodName;
//    private boolean latest;
//    private String value;
//    private Notes[] footnotes;
//}
//
//@AllArgsConstructor
//@Getter
//@Setter
//@JsonPOJOBuilder
//@JsonDeserialize
//public class Notes {
//    private String code;
//    private String text;
//}
