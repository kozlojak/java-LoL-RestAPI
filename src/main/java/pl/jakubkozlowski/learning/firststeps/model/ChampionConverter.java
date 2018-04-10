package pl.jakubkozlowski.learning.firststeps.model;

public interface ChampionConverter {
    ChampionDTO convert (Champion champion);
    Champion convert (ChampionDTO championDTO);
}
