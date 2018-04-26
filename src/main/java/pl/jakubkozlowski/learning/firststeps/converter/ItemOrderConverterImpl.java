package pl.jakubkozlowski.learning.firststeps.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.jakubkozlowski.learning.firststeps.dto.ItemOrderDTO;
import pl.jakubkozlowski.learning.firststeps.model.ItemOrderEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemOrderConverterImpl implements ItemOrderConverter {

    private ModelMapper modelMapper;

    @Autowired
    public ItemOrderConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public ItemOrderDTO convert(ItemOrderEntity itemOrderEntity) {
        return (itemOrderEntity == null) ? null : modelMapper.map(itemOrderEntity, ItemOrderDTO.class);
    }

    @Override
    public ItemOrderEntity convert(ItemOrderDTO itemOrderDTO) {
        return (itemOrderDTO == null) ? null : modelMapper.map(itemOrderDTO, ItemOrderEntity.class);
    }

    @Override
    public List<ItemOrderDTO> convertListEntity(List<ItemOrderEntity> itemOrderEntityList) {
        if (CollectionUtils.isEmpty(itemOrderEntityList))
            return Collections.emptyList();
        else
            return itemOrderEntityList.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<ItemOrderEntity> convertListDTO(List<ItemOrderDTO> itemOrderDtoList) {
        if (CollectionUtils.isEmpty(itemOrderDtoList))
            return Collections.emptyList();
        else
            return itemOrderDtoList.stream().map(this::convert).collect(Collectors.toList());
    }
}