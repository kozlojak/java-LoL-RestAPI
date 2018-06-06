package pl.jakubkozlowski.leagueoflegends.restAPI.service;

import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ChampionDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.shared.Page;
import pl.jakubkozlowski.leagueoflegends.restAPI.shared.Pageable;

import java.util.Optional;

public interface ChampionService extends CRUDService<ChampionDTO> {

    Optional<Page<ChampionDTO>> findPage(Pageable pageable);
}
