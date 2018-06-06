package pl.jakubkozlowski.leagueoflegends.restAPI.converter;

import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ChampionDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ChampionEntity;

public interface ChampionConverter extends ModelMapperConverter<ChampionDTO, ChampionEntity> {
}
