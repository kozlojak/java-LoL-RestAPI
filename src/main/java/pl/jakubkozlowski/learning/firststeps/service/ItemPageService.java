package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.ItemPageDTO;

import java.util.List;

public interface ItemPageService extends CRUDservice<ItemPageDTO> {

    List<ItemPageDTO> findAll();

    ItemPageDTO findByPagename(String name);
}
