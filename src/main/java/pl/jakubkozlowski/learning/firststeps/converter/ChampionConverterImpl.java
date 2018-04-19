package pl.jakubkozlowski.learning.firststeps.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.jakubkozlowski.learning.firststeps.dto.ChampionDTO;
import pl.jakubkozlowski.learning.firststeps.model.ChampionEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChampionConverterImpl implements ChampionConverter {

    private ModelMapper modelMapper;

    @Autowired
    public ChampionConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ChampionDTO convert(ChampionEntity championEntity) {
        return (championEntity == null) ? null : modelMapper.map(championEntity, ChampionDTO.class);
    }

    @Override
    public ChampionEntity convert(ChampionDTO championDTO) {
        return (championDTO == null) ? null : modelMapper.map(championDTO, ChampionEntity.class);
    }

    @Override
    public List<ChampionDTO> convertListEntity(List<ChampionEntity> championEntityList) {
        if (CollectionUtils.isEmpty(championEntityList))
            return Collections.emptyList();
        else
            return championEntityList.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChampionEntity> convertListDTO(List<ChampionDTO> championDTOList) {
        if (CollectionUtils.isEmpty(championDTOList))
            return Collections.emptyList();
        else
            return championDTOList.stream().map(this::convert).collect(Collectors.toList());
    }

}
