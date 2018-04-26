package pl.jakubkozlowski.learning.firststeps.converter;

import pl.jakubkozlowski.learning.firststeps.dto.ItemPageDTO;
import pl.jakubkozlowski.learning.firststeps.model.ItemPageEntity;

import java.util.List;

public interface ItemPageConverter {

    ItemPageDTO convert(ItemPageEntity itemPageEntity);

    ItemPageEntity convert(ItemPageDTO itemPageDTO);

    List<ItemPageDTO> convertListEntity(List<ItemPageEntity> itemPageEntityList);

    List<ItemPageEntity> convertListDTO(List<ItemPageDTO> itemPageDTOList);
}
