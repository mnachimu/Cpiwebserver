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
public class Notes {
    @JsonProperty("code")
    private String code;
    @JsonProperty("text")
    private String text;
}
