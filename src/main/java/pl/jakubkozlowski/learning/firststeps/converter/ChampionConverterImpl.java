package pl.jakubkozlowski.learning.firststeps.converter;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.jakubkozlowski.learning.firststeps.DTO.ChampionDTO;
import pl.jakubkozlowski.learning.firststeps.model.ChampionEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChampionConverterImpl implements ChampionConverter {


    @Override
    public ChampionDTO convert(ChampionEntity championEntity) {
        if (championEntity == null) {
            return null;
        }
        return new ChampionDTO(championEntity.getId(), championEntity.getName());
    }

    @Override
    public ChampionEntity convert(ChampionDTO championDTO) {

        return new ChampionEntity(championDTO.getId(), championDTO.getName());
    }

    @Override
    public List<ChampionDTO> convertListEntity(List<ChampionEntity> championEntityList) {
        if (CollectionUtils.isEmpty(championEntityList)) {
            return Collections.emptyList();
        }
        return championEntityList.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChampionEntity> convertListDTO(List<ChampionDTO> championDTOList) {
        if (CollectionUtils.isEmpty(championDTOList)) {
            return Collections.emptyList();
        }
        return championDTOList.stream().map(this::convert).collect(Collectors.toList());
    }

}
