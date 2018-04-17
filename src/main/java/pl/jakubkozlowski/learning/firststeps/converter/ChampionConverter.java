package pl.jakubkozlowski.learning.firststeps.converter;

import pl.jakubkozlowski.learning.firststeps.DTO.ChampionDTO;
import pl.jakubkozlowski.learning.firststeps.model.ChampionEntity;

import java.util.List;

public interface ChampionConverter {

    ChampionDTO convert(ChampionEntity championEntity);

    ChampionEntity convert(ChampionDTO championDTO);

    List<ChampionDTO> convertListEntity(List<ChampionEntity> championEntityList);

    List<ChampionEntity> convertListDTO(List<ChampionDTO> championDTOList);
}
