package pl.jakubkozlowski.leagueoflegends.restAPI.service;

import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ItemOrderDTO;

import java.util.List;
import java.util.Optional;

public interface ItemOrderService extends CRUDService<ItemOrderDTO> {

    Optional<List<ItemOrderDTO>> findAll();
}
