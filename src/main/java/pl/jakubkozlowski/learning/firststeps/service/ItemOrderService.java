package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.ItemOrderDTO;

import java.util.List;
import java.util.Optional;

public interface ItemOrderService extends CRUDService<ItemOrderDTO> {

    Optional<List<ItemOrderDTO>> findAll();
}
