package pl.jakubkozlowski.learning.firststeps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubkozlowski.learning.firststeps.mapper.ChampionMapper;
import pl.jakubkozlowski.learning.firststeps.model.Champion;
import pl.jakubkozlowski.learning.firststeps.model.ChampionConverter;
import pl.jakubkozlowski.learning.firststeps.model.ChampionConverterImpl;
import pl.jakubkozlowski.learning.firststeps.model.ChampionDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChampionServiceImpl implements ChampionService {

    public ChampionServiceImpl(ChampionMapper championMapper){
        this.championMapper=championMapper;
    }

    @Autowired
    private ChampionMapper championMapper;

    private ChampionConverter championConverter=new ChampionConverterImpl();


    @Override
    public List<ChampionDTO> findAll() {
        List<Champion> championList=championMapper.findAll();
        List<ChampionDTO> championDTOList=new ArrayList<>(championList.size());
        for(Champion champion : championList){
            championDTOList.add(championConverter.convert(champion));
        }
        return championDTOList;

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