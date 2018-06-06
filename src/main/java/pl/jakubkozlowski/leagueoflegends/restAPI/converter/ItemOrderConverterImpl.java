package pl.jakubkozlowski.leagueoflegends.restAPI.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ItemOrderDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ItemOrderEntity;

@Component
public class ItemOrderConverterImpl implements ItemOrderConverter {

    private ModelMapper modelMapper;

    @Autowired
    public ItemOrderConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Class<ItemOrderEntity> getEntityClass() {
        return ItemOrderEntity.class;
    }

    @Override
    public Class<ItemOrderDTO> getDTOClass() {
        return ItemOrderDTO.class;
    }

    @Override
    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}