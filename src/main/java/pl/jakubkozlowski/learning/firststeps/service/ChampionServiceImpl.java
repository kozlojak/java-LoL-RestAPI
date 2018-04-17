package pl.jakubkozlowski.learning.firststeps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubkozlowski.learning.firststeps.mapper.ChampionMapper;
import pl.jakubkozlowski.learning.firststeps.model.ChampionEntity;
import pl.jakubkozlowski.learning.firststeps.model.ChampionConverter;
import pl.jakubkozlowski.learning.firststeps.model.ChampionDTO;

import java.util.List;

@Service
public class ChampionServiceImpl implements ChampionService {

    private ChampionMapper championMapper;
    private ChampionConverter championConverter;

    @Autowired
    public ChampionServiceImpl(ChampionMapper championMapper, ChampionConverter championConverter){
        this.championMapper=championMapper;
        this.championConverter = championConverter;
    }

    @Override
    public List<ChampionDTO> findAll() {
        List<ChampionEntity> championEntityList =championMapper.findAll();
        return championConverter.convertListEntity(championEntityList);
    }

    @Override
    public ChampionDTO findById(Long id){
        return championConverter.convert(championMapper.findById(id));
    }

    @Override
    public void persist(ChampionDTO championDTO) {
        championMapper.persist(championConverter.convert(championDTO));
    }

    @Override
    public void update(Long id, ChampionDTO championDTO) {
        championMapper.update(id, championConverter.convert(championDTO));
    }

    @Override
    public void deleteById(Long id) {
        championMapper.deleteById(id);
    }


}