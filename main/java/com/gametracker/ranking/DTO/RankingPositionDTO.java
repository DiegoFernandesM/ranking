package com.gametracker.ranking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingPositionDTO {
    private int position;
    private double score;
    private String rank;
}
