package com.orisinterview.cpiwebserver.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonPOJOBuilder
@JsonDeserialize
@NoArgsConstructor
public class Result {
    @JsonProperty("series")
    private List<Series> series;
}
