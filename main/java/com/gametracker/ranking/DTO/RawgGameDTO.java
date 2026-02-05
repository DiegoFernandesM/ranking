package com.gametracker.ranking.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RawgGameDTO {

    private Long id;
    private String name;
    private Double rating;
    private List<RawgGenreDTO> genres;

    public String getName() {return name; }

    public double getRating() {return rating; }

    public List<RawgGenreDTO> getGenres() {return genres;}

    public Long getId() {return id;}
}
