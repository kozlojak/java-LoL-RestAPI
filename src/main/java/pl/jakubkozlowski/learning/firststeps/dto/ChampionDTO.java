package pl.jakubkozlowski.learning.firststeps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChampionDTO {
    private Long id;
    private String name;
}
