package pl.jakubkozlowski.learning.firststeps.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.learning.firststeps.converter.ItemOrderConverter;
import pl.jakubkozlowski.learning.firststeps.dto.ItemOrderDTO;
import pl.jakubkozlowski.learning.firststeps.mapper.ItemOrderMapper;
import pl.jakubkozlowski.learning.firststeps.model.ItemOrderEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static pl.jakubkozlowski.learning.firststeps.descriptor.ItemOrderTestConstants.*;

@RunWith(SpringRunner.class)
public class ItemOrderServiceImplTest {

    @Autowired
    private ItemOrderService itemOrderService;

    @Autowired
    private ItemOrderMapper itemOrderMapper;

    @Autowired
    private ItemOrderConverter itemOrderConverter;
    private ItemOrderEntity itemOrderEntitySet1;
    private ItemOrderDTO itemOrderDTOSet1;
    private ItemOrderEntity itemOrderEntitySet2;
    private ItemOrderDTO itemOrderDTOSet2;
    private List<ItemOrderEntity> itemOrderEntityList;
    private List<ItemOrderDTO> itemOrderDTOList;

    @Before
    public void setUp() throws Exception {
        itemOrderEntitySet1 = new ItemOrderEntity(ID_1, ITEM_1, ORDER_1);
        itemOrderDTOSet1 = new ItemOrderDTO(ID_1, ITEM_1, ORDER_1);
        itemOrderEntitySet2 = new ItemOrderEntity(ID_2, ITEM_2, ORDER_2);
        itemOrderDTOSet2 = new ItemOrderDTO(ID_2, ITEM_2, ORDER_2);
        itemOrderEntityList = Arrays.asList(itemOrderEntitySet1, itemOrderEntitySet2);
        itemOrderDTOList = Arrays.asList(itemOrderDTOSet1, itemOrderDTOSet2);

        Mockito.when(itemOrderMapper.findAll())
                .thenReturn(itemOrderEntityList);
        Mockito.when(itemOrderMapper.findById(itemOrderEntitySet1.getItemPageId()))
                .thenReturn(itemOrderEntitySet1);
        Mockito.when(itemOrderConverter.convert(itemOrderDTOSet1))
                .thenReturn(itemOrderEntitySet1);
        Mockito.when(itemOrderConverter.convert(itemOrderEntitySet1))
                .thenReturn(itemOrderDTOSet1);
        Mockito.when(itemOrderConverter.convertListDTO(itemOrderDTOList))
                .thenReturn(itemOrderEntityList);
        Mockito.when(itemOrderConverter.convertListEntity(itemOrderEntityList))
                .thenReturn(itemOrderDTOList);
    }

    @Test
    public void whenSaveItemOrder_thenReturnItemOrderDTO() {
        //when
        ItemOrderDTO actual = itemOrderService.save(itemOrderDTOSet1);
        //then
        assertThat(actual)
                .isEqualTo(itemOrderDTOSet1);
    }

    @Test
    public void whenFindAllItemOrders_thenReturnListOfItemOrdersDTO() {
        //when
        List<ItemOrderDTO> actual = itemOrderService.findAll();
        //then
        assertThat(actual)
                .isEqualTo(itemOrderDTOList);
    }

    @Test
    public void whenFindItemOrderById_thenReturnItemOrderDTO() {
        //when
        ItemOrderDTO actual = itemOrderService.findById(itemOrderEntitySet1.getItemPageId());
        //then
        assertThat(actual)
                .isEqualTo(itemOrderDTOSet1);
    }

    @Test
    public void whenUpdateItemOrder_thenReturnUpdatedItemOrderDTO() {
        //when
        itemOrderService.update(ID_1, itemOrderDTOSet1);
        //then
        Mockito.verify(itemOrderMapper, Mockito.times(1)).update(ID_1, itemOrderEntitySet1);
    }

    @Test
    public void whenDeleteById_thenDeleteItemOrderDTO() {
        //when
        itemOrderService.deleteById(ID_1);
        //then
        Mockito.verify(itemOrderMapper, Mockito.times(1)).deleteById(ID_1);
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
}