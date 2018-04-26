package pl.jakubkozlowski.learning.firststeps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubkozlowski.learning.firststeps.converter.ChampionConverter;
import pl.jakubkozlowski.learning.firststeps.dto.ChampionDTO;
import pl.jakubkozlowski.learning.firststeps.mapper.ChampionMapper;
import pl.jakubkozlowski.learning.firststeps.model.ChampionEntity;

import java.util.List;

@Service
public class ChampionServiceImpl implements ChampionService {

    private ChampionMapper championMapper;
    private ChampionConverter championConverter;

    @Autowired
    public ChampionServiceImpl(ChampionMapper championMapper, ChampionConverter championConverter) {
        this.championMapper = championMapper;
        this.championConverter = championConverter;
    }

    @Override
    public List<ChampionDTO> findAll() {
        return championConverter.convertListEntity(championMapper.findAll());
    }

    @Override
    public ChampionDTO findById(Long id) {
        return championConverter.convertEntity(championMapper.findById(id));
    }

    @Override
    public ChampionDTO save(ChampionDTO championDTO) {
        ChampionEntity championEntity = championConverter.convertDTO(championDTO);
        championMapper.save(championEntity);
        return championConverter.convertEntity(championEntity);
    }

    @Override
    public void update(Long id, ChampionDTO championDTO) {
        championMapper.update(id, championConverter.convertDTO(championDTO));
    }

    @Override
    public void deleteById(Long id) {
        championMapper.deleteById(id);
    }


}