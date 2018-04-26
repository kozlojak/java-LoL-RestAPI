package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.ChampionDTO;

import java.util.List;

public interface ChampionService extends CRUDService<ChampionDTO> {

    List<ChampionDTO> findAll();
}
