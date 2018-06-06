package pl.jakubkozlowski.leagueoflegends.restAPI.service;

import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ItemPageDTO;

import java.util.List;
import java.util.Optional;

public interface ItemPageService extends CRUDService<ItemPageDTO> {

    Optional<List<ItemPageDTO>> findAll();

    Optional<ItemPageDTO> findByPagename(String name);
}
