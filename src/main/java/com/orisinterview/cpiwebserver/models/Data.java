package com.orisinterview.cpiwebserver.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonPOJOBuilder
@JsonDeserialize
@NoArgsConstructor
public class Data {
    @JsonProperty("year")
    private String year;
    @JsonProperty("period")
    private String period;
    @JsonProperty("periodName")
    private String periodName;
    @JsonProperty("latest")
    private boolean latest;
    @JsonProperty("value")
    private String value;
    @JsonProperty("footnotes")
    private Notes[] footnotes;

}
