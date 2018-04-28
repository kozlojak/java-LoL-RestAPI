package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.ChampionDTO;

import java.util.List;
import java.util.Optional;

public interface ChampionService extends CRUDService<ChampionDTO> {

    Optional<List<ChampionDTO>> findAll();
}
