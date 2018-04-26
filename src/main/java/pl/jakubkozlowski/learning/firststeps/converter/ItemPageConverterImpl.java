package pl.jakubkozlowski.learning.firststeps.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakubkozlowski.learning.firststeps.dto.ItemPageDTO;
import pl.jakubkozlowski.learning.firststeps.model.ItemPageEntity;

@Component
public class ItemPageConverterImpl implements ItemPageConverter {

    private ModelMapper modelMapper;

    @Autowired
    public ItemPageConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Class<ItemPageEntity> getEntityClass() {
        return ItemPageEntity.class;
    }

    @Override
    public Class<ItemPageDTO> getDTOClass() {
        return ItemPageDTO.class;
    }

    @Override
    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}

