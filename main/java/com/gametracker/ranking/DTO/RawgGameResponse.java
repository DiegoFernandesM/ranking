package com.gametracker.ranking.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)

public class RawgGameResponse {
    private List<RawgGameDTO> results;
    public List<RawgGameDTO> getResults() {
        return results;
    }

    public void setResults(List<RawgGameDTO> results) {
        this.results = results;
    }
}

