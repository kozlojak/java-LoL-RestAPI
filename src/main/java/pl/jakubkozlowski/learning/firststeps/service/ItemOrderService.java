package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.ItemOrderDTO;

import java.util.List;

public interface ItemOrderService {

    ItemOrderDTO save(ItemOrderDTO itemOrderDTO);

    List<ItemOrderDTO> findAll();

    ItemOrderDTO findById(Long id);

    void update(Long id, ItemOrderDTO ItemOrderDTO);

    void deleteById(Long id);
}
