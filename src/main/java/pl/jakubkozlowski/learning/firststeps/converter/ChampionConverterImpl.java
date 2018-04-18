package pl.jakubkozlowski.learning.firststeps.converter;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.jakubkozlowski.learning.firststeps.dto.ChampionDTO;
import pl.jakubkozlowski.learning.firststeps.model.ChampionEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChampionConverterImpl implements ChampionConverter {


    @Override
    public ChampionDTO convert(ChampionEntity championEntity) {
        return (championEntity == null) ? null : new ChampionDTO(championEntity.getId(), championEntity.getName());
    }

    @Override
    public ChampionEntity convert(ChampionDTO championDTO) {
        return (championDTO == null) ? null : new ChampionEntity(championDTO.getId(), championDTO.getName());
    }

    @Override
    public List<ChampionDTO> convertListEntity(List<ChampionEntity> championEntityList) {
        return (CollectionUtils.isEmpty(championEntityList)) ? Collections.emptyList()
                : championEntityList.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChampionEntity> convertListDTO(List<ChampionDTO> championDTOList) {
        return (CollectionUtils.isEmpty(championDTOList)) ? Collections.emptyList()
                : championDTOList.stream().map(this::convert).collect(Collectors.toList());
    }

}
