package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.model.ChampionDTO;

import java.util.List;

public interface ChampionService {

    List<ChampionDTO> findAll();

    ChampionDTO findById(Long id);

    void persist(ChampionDTO championDTO);

    void update(Long id, ChampionDTO championDTO);

    void deleteById(Long id);
}
