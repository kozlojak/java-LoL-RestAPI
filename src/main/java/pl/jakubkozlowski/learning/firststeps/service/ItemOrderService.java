package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.ItemOrderDTO;

import java.util.List;

public interface ItemOrderService extends CRUDservice<ItemOrderDTO> {

    List<ItemOrderDTO> findAll();
}
