package pl.jakubkozlowski.leagueoflegends.restAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubkozlowski.leagueoflegends.restAPI.converter.ItemPageConverter;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ItemPageDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.mapper.ItemPageMapper;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ItemPageEntity;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPageServiceImpl implements ItemPageService {

    private ItemPageMapper itemPageMapper;
    private ItemPageConverter itemPageConverter;

    @Autowired
    public ItemPageServiceImpl(ItemPageMapper itemPageMapper, ItemPageConverter itemPageConverter) {
        this.itemPageMapper = itemPageMapper;
        this.itemPageConverter = itemPageConverter;
    }

    @Override
    public Optional<List<ItemPageDTO>> findAll() {
        return Optional.ofNullable(itemPageConverter.convertListEntity(itemPageMapper.findAll()));
    }

    @Override
    public Optional<ItemPageDTO> findById(Long id) {
        return Optional.ofNullable(itemPageConverter.convertEntity(itemPageMapper.findById(id)));
    }

    @Override
    public ItemPageDTO save(ItemPageDTO itemPageDTO) {
        ItemPageEntity userEntity = itemPageConverter.convertDTO(itemPageDTO);
        itemPageMapper.save(userEntity);
        return itemPageConverter.convertEntity(userEntity);
    }

    @Override
    public void update(Long id, ItemPageDTO itemPageDTO) {
        itemPageMapper.update(id, itemPageConverter.convertDTO(itemPageDTO));
    }

    @Override
    public void deleteById(Long id) {
        itemPageMapper.deleteById(id);
    }

    @Override
    public Optional<ItemPageDTO> findByPagename(String pagename) {
        return Optional.ofNullable(itemPageConverter.convertEntity(itemPageMapper.findByPagename(pagename)));
    }
}
