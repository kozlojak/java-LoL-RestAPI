package pl.jakubkozlowski.learning.firststeps.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.jakubkozlowski.learning.firststeps.dto.ItemPageDTO;
import pl.jakubkozlowski.learning.firststeps.model.ItemPageEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemPageConverterImpl implements ItemPageConverter {

    private ModelMapper modelMapper;

    @Autowired
    public ItemPageConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemPageDTO convert(ItemPageEntity itemPageEntity) {
        return (itemPageEntity == null) ? null : modelMapper.map(itemPageEntity, ItemPageDTO.class);
    }

    @Override
    public ItemPageEntity convert(ItemPageDTO itemPageDTO) {
        return (itemPageDTO == null) ? null : modelMapper.map(itemPageDTO, ItemPageEntity.class);
    }

    @Override
    public List<ItemPageDTO> convertListEntity(List<ItemPageEntity> itemPageEntityList) {
        if (CollectionUtils.isEmpty(itemPageEntityList))
            return Collections.emptyList();
        else
            return itemPageEntityList.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<ItemPageEntity> convertListDTO(List<ItemPageDTO> itemPageDTOList) {
        if (CollectionUtils.isEmpty(itemPageDTOList))
            return Collections.emptyList();
        else
            return itemPageDTOList.stream().map(this::convert).collect(Collectors.toList());
    }

}
