package pl.jakubkozlowski.leagueoflegends.restAPI.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.leagueoflegends.restAPI.converter.ItemOrderConverter;
import pl.jakubkozlowski.leagueoflegends.restAPI.descriptor.ItemOrderTestConstants;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ItemOrderDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.mapper.ItemOrderMapper;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ItemOrderEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ItemOrderServiceImplTest {

    @Autowired
    private ItemOrderService itemOrderService;

    @Autowired
    private ItemOrderMapper itemOrderMapper;

    @Autowired
    private ItemOrderConverter itemOrderConverter;

    @Test
    public void whenFindAll_thenReturnListOfItemOrdersDTO() {
        //when
        List<ItemOrderDTO> actual = itemOrderService.findAll().get();
        //then
        assertThat(actual)
                .isEqualTo(itemOrderDTOList);
    }

    @Before
    public void setUp() throws Exception {
        itemOrderEntitySet1 = new ItemOrderEntity(ItemOrderTestConstants.ID_1, ItemOrderTestConstants.ITEM_1, ItemOrderTestConstants.ORDER_1);
        itemOrderDTOSet1 = new ItemOrderDTO(ItemOrderTestConstants.ID_1, ItemOrderTestConstants.ITEM_1, ItemOrderTestConstants.ORDER_1);
        itemOrderEntitySet2 = new ItemOrderEntity(ItemOrderTestConstants.ID_2, ItemOrderTestConstants.ITEM_2, ItemOrderTestConstants.ORDER_2);
        itemOrderDTOSet2 = new ItemOrderDTO(ItemOrderTestConstants.ID_2, ItemOrderTestConstants.ITEM_2, ItemOrderTestConstants.ORDER_2);
        itemOrderEntityList = Arrays.asList(itemOrderEntitySet1, itemOrderEntitySet2);
        itemOrderDTOList = Arrays.asList(itemOrderDTOSet1, itemOrderDTOSet2);

        Mockito.when(itemOrderMapper.findAll())
                .thenReturn(itemOrderEntityList);
        Mockito.when(itemOrderMapper.findById(itemOrderEntitySet1.getItemPageId()))
                .thenReturn(itemOrderEntitySet1);
        Mockito.when(itemOrderConverter.convertDTO(itemOrderDTOSet1))
                .thenReturn(itemOrderEntitySet1);
        Mockito.when(itemOrderConverter.convertEntity(itemOrderEntitySet1))
                .thenReturn(itemOrderDTOSet1);
        Mockito.when(itemOrderConverter.convertListDTO(itemOrderDTOList))
                .thenReturn(itemOrderEntityList);
        Mockito.when(itemOrderConverter.convertListEntity(itemOrderEntityList))
                .thenReturn(itemOrderDTOList);
    }

    private ItemOrderEntity itemOrderEntitySet1;
    private ItemOrderDTO itemOrderDTOSet1;
    private ItemOrderEntity itemOrderEntitySet2;
    private ItemOrderDTO itemOrderDTOSet2;
    private List<ItemOrderEntity> itemOrderEntityList;
    private List<ItemOrderDTO> itemOrderDTOList;

    @Test
    public void whenFindById_thenReturnItemOrderDTO() {
        //when
        ItemOrderDTO actual = itemOrderService.findById(itemOrderEntitySet1.getItemPageId()).get();
        //then
        assertThat(actual)
                .isEqualTo(itemOrderDTOSet1);
    }

    @Test
    public void whenSave_thenReturnItemOrderDTO() {
        //when
        ItemOrderDTO actual = itemOrderService.save(itemOrderDTOSet1);
        //then
        assertThat(actual)
                .isEqualTo(itemOrderDTOSet1);
    }

    @TestConfiguration
    static class ItemOrderServiceImplTestContextConfiguration {

        @MockBean
        private ItemOrderMapper itemOrderMapper;

        @MockBean
        private ItemOrderConverter itemOrderConverter;

        @Bean
        public ItemOrderService itemOrderService() {
            return new ItemOrderServiceImpl(itemOrderMapper, itemOrderConverter);
        }

    }

    @Test
    public void whenUpdate_thenReturnUpdatedItemOrderDTO() {
        //when
        itemOrderService.update(ItemOrderTestConstants.ID_1, itemOrderDTOSet1);
        //then
        Mockito.verify(itemOrderMapper, Mockito.times(1)).update(ItemOrderTestConstants.ID_1, itemOrderEntitySet1);
    }

    @Test
    public void whenDeleteById_thenDeleteItemOrderDTO() {
        //when
        itemOrderService.deleteById(ItemOrderTestConstants.ID_1);
        //then
        Mockito.verify(itemOrderMapper, Mockito.times(1)).deleteById(ItemOrderTestConstants.ID_1);
    }
}