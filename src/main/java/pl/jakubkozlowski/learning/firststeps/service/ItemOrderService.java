package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.ItemOrderDTO;

import java.util.List;

public interface ItemOrderService extends CRUDService<ItemOrderDTO> {

    List<ItemOrderDTO> findAll();
}
