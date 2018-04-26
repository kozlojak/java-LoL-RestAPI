package pl.jakubkozlowski.learning.firststeps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubkozlowski.learning.firststeps.converter.ItemPageConverter;
import pl.jakubkozlowski.learning.firststeps.dto.ItemPageDTO;
import pl.jakubkozlowski.learning.firststeps.mapper.ItemPageMapper;
import pl.jakubkozlowski.learning.firststeps.model.ItemPageEntity;

import java.util.List;

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
    public List<ItemPageDTO> findAll() {
        return itemPageConverter.convertListEntity(itemPageMapper.findAll());
    }

    @Override
    public ItemPageDTO findById(Long id) {
        return itemPageConverter.convertEntity(itemPageMapper.findById(id));
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
    public ItemPageDTO findByPagename(String pagename) {
        return itemPageConverter.convertEntity(itemPageMapper.findByPagename(pagename));
    }
}
