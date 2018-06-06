package pl.jakubkozlowski.leagueoflegends.restAPI.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ChampionDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ChampionEntity;

@Component
public class ChampionConverterImpl implements ChampionConverter {

    private ModelMapper modelMapper;

    @Autowired
    public ChampionConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Class<ChampionEntity> getEntityClass() {
        return ChampionEntity.class;
    }

    @Override
    public Class<ChampionDTO> getDTOClass() {
        return ChampionDTO.class;
    }

    @Override
    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
