package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.ChampionDTO;
import pl.jakubkozlowski.learning.firststeps.shared.Page;
import pl.jakubkozlowski.learning.firststeps.shared.Pageable;

import java.util.Optional;

public interface ChampionService extends CRUDService<ChampionDTO> {

    Optional<Page<ChampionDTO>> findPage(Pageable pageable);
}
