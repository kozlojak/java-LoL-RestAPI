package pl.jakubkozlowski.learning.firststeps.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChampionEntity {
    private Long id;
    private String name;
}
