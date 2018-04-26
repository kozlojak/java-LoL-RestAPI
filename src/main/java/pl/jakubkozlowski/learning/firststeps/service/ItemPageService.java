package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.ItemPageDTO;

import java.util.List;

public interface ItemPageService {

    ItemPageDTO saveItemPage(ItemPageDTO itemPageDTO);

    List<ItemPageDTO> findAllItemPages();

    ItemPageDTO findItemPageById(Long id);

    ItemPageDTO findItemPageByPagename(String name);

    void updateItemPage(Long id, ItemPageDTO ItemPageDTO);

    void deleteById(Long id);
}
