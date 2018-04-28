package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.ItemPageDTO;

import java.util.List;
import java.util.Optional;

public interface ItemPageService extends CRUDService<ItemPageDTO> {

    Optional<List<ItemPageDTO>> findAll();

    Optional<ItemPageDTO> findByPagename(String name);
}
