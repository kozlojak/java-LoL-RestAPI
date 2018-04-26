package pl.jakubkozlowski.learning.firststeps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubkozlowski.learning.firststeps.converter.ItemOrderConverter;
import pl.jakubkozlowski.learning.firststeps.dto.ItemOrderDTO;
import pl.jakubkozlowski.learning.firststeps.mapper.ItemOrderMapper;
import pl.jakubkozlowski.learning.firststeps.model.ItemOrderEntity;

import java.util.List;

@Service
public class ItemOrderServiceImpl implements ItemOrderService {

    private ItemOrderMapper itemOrderMapper;
    private ItemOrderConverter itemOrderConverter;

    @Autowired
    public ItemOrderServiceImpl(ItemOrderMapper itemOrderMapper, ItemOrderConverter itemOrderConverter) {
        this.itemOrderMapper = itemOrderMapper;
        this.itemOrderConverter = itemOrderConverter;
    }

    @Override
    public ItemOrderDTO save(ItemOrderDTO itemOrderDTO) {
        ItemOrderEntity userEntity = itemOrderConverter.convert(itemOrderDTO);
        itemOrderMapper.save(userEntity);
        return itemOrderConverter.convert(userEntity);
    }

    @Override
    public List<ItemOrderDTO> findAll() {
        return itemOrderConverter.convertListEntity(itemOrderMapper.findAll());
    }

    @Override
    public ItemOrderDTO findById(Long id) {
        return itemOrderConverter.convert(itemOrderMapper.findById(id));
    }


    @Override
    public void update(Long id, ItemOrderDTO itemOrderDTO) {
        itemOrderMapper.update(id, itemOrderConverter.convert(itemOrderDTO));
    }

    @Override
    public void deleteById(Long id) {
        itemOrderMapper.deleteById(id);
    }
}
