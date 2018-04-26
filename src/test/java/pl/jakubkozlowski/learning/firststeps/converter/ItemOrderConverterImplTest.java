package pl.jakubkozlowski.learning.firststeps.converter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.learning.firststeps.dto.ItemOrderDTO;
import pl.jakubkozlowski.learning.firststeps.model.ItemOrderEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jakubkozlowski.learning.firststeps.descriptor.ItemOrderTestConstants.*;

@RunWith(SpringRunner.class)
public class ItemOrderConverterImplTest {

    @Autowired
    private ItemOrderConverter itemOrderConverter;
    private ItemOrderEntity itemOrderEntitySet1;
    private ItemOrderDTO itemOrderDTOSet1;
    private ItemOrderEntity itemOrderEntitySet2;
    private ItemOrderDTO itemOrderDTOSet2;
    private List<ItemOrderEntity> itemOrderEntityList;
    private List<ItemOrderDTO> itemOrderDTOList;

    @Before
    public void SetUp() throws Exception {
        itemOrderEntitySet1 = new ItemOrderEntity(ID_1, ITEM_1, ORDER_1);
        itemOrderDTOSet1 = new ItemOrderDTO(ID_1, ITEM_1, ORDER_1);
        itemOrderEntitySet2 = new ItemOrderEntity(ID_2, ITEM_2, ORDER_2);
        itemOrderDTOSet2 = new ItemOrderDTO(ID_2, ITEM_2, ORDER_2);
        itemOrderEntityList = Arrays.asList(itemOrderEntitySet1, itemOrderEntitySet2);
        itemOrderDTOList = Arrays.asList(itemOrderDTOSet1, itemOrderDTOSet2);
    }

    @Test
    public void whenConvertUserEntity_thenReturnUserDTO() {
        //when
        ItemOrderDTO actual = itemOrderConverter.convert(itemOrderEntitySet1);
        //then
        assertThat(actual)
                .isEqualTo(itemOrderDTOSet1);
    }

    @Test
    public void whenConvertChampionDTO_thenReturnChampionEntity() {
        //when
        ItemOrderEntity actual = itemOrderConverter.convert(itemOrderDTOSet1);
        //then
        assertThat(actual)
                .isEqualTo(itemOrderEntitySet1);
    }

    @Test
    public void whenConvertChampionListEntity_thenReturnChampionListDTO() {
        //when
        List<ItemOrderDTO> actual = itemOrderConverter.convertListEntity(itemOrderEntityList);
        //then
        assertThat(actual)
                .isEqualTo(itemOrderDTOList);
    }

    @Test
    public void whenConvertChampionListDTO_thenReturnChampionListEntity() {
        //when
        List<ItemOrderEntity> actual = itemOrderConverter.convertListDTO(itemOrderDTOList);
        //then
        assertThat(actual)
                .isEqualTo(itemOrderEntityList);
    }

    @TestConfiguration
    static class ItemOrderConverterImplTestContextConfiguration {

        @Autowired
        private ModelMapper modelMapper;

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public ItemOrderConverter itemOrderConverter() {
            return new ItemOrderConverterImpl(modelMapper);
        }
    }
}