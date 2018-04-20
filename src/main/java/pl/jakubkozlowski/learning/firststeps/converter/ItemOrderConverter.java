package pl.jakubkozlowski.learning.firststeps.converter;

import pl.jakubkozlowski.learning.firststeps.dto.ItemOrderDTO;
import pl.jakubkozlowski.learning.firststeps.model.ItemOrderEntity;

import java.util.List;

public interface ItemOrderConverter {

    ItemOrderDTO convert(ItemOrderEntity itemOrderEntity);

    ItemOrderEntity convert(ItemOrderDTO itemOrderDTO);

    List<ItemOrderDTO> convertListEntity(List<ItemOrderEntity> itemOrderEntityList);

    List<ItemOrderEntity> convertListDTO(List<ItemOrderDTO> itemOrderDtoList);
}
