package pl.jakubkozlowski.leagueoflegends.restAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubkozlowski.leagueoflegends.restAPI.converter.ChampionConverter;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ChampionDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.mapper.ChampionMapper;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ChampionEntity;
import pl.jakubkozlowski.leagueoflegends.restAPI.shared.Page;
import pl.jakubkozlowski.leagueoflegends.restAPI.shared.Pageable;

import java.util.Optional;

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
    public Optional<Page<ChampionDTO>> findPage(Pageable pageable) {
        return Optional.ofNullable(championConverter.convertPageEntity(championMapper.findPage(pageable)));
    }

    @Override
    public Optional<ChampionDTO> findById(Long id) {
        return Optional.ofNullable(championConverter.convertEntity(championMapper.findById(id)));
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