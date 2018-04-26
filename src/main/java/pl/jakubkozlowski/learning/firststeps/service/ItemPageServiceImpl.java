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
    public ItemPageDTO saveItemPage(ItemPageDTO itemPageDTO) {
        ItemPageEntity userEntity = itemPageConverter.convert(itemPageDTO);
        itemPageMapper.saveItemPage(userEntity);
        return itemPageConverter.convert(userEntity);
    }

    @Override
    public List<ItemPageDTO> findAllItemPages() {
        return itemPageConverter.convertListEntity(itemPageMapper.findAllItemPages());
    }

    @Override
    public ItemPageDTO findItemPageById(Long id) {
        return itemPageConverter.convert(itemPageMapper.findItemPageById(id));
    }

    @Override
    public ItemPageDTO findItemPageByPagename(String pagename) {
        return itemPageConverter.convert(itemPageMapper.findItemPageByPagename(pagename));
    }

    @Override
    public void updateItemPage(Long id, ItemPageDTO itemPageDTO) {
        itemPageMapper.updateItemPage(id, itemPageConverter.convert(itemPageDTO));
    }

    @Override
    public void deleteById(Long id) {
        itemPageMapper.deleteItemPageById(id);
    }
}
