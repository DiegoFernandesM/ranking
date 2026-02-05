package com.gametracker.ranking.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)

@Getter
public class RawgGenreDTO {
    private String name;

}