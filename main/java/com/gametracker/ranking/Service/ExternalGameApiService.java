package com.gametracker.ranking.Service;

import com.gametracker.ranking.DTO.RawgGameDTO;
import com.gametracker.ranking.DTO.RawgGameResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class ExternalGameApiService {

    private final RestClient restClient;
    private final String apiKey;

    public ExternalGameApiService(@Value("${rawg.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.restClient = RestClient.create("https://api.rawg.io/api");
    }

    public Optional<RawgGameDTO> buscarPrimeiroJogo(String nome) {
        RawgGameResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/games")
                        .queryParam("search", nome)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .body(RawgGameResponse.class);

        if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(response.getResults().get(0));
    }
}
