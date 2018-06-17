package pl.jakubkozlowski.leagueoflegends.restAPI.converter;

import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ItemOrderDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ItemOrderEntity;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static pl.jakubkozlowski.leagueoflegends.restAPI.descriptor.ItemOrderTestConstants.*;

@RunWith(SpringRunner.class)
public class ItemOrderConverterImplTest extends ModelMapperConverterTest<ItemOrderDTO, ItemOrderEntity> {

    @Autowired
    private ItemOrderConverter itemOrderConverter;

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

    @Override
    public ItemOrderDTO prepareDTO() {
        return new ItemOrderDTO(ID_1, ITEM_1, ORDER_1);
    }

    @Override
    public ItemOrderEntity prepareEntity() {
        return new ItemOrderEntity(ID_1, ITEM_1, ORDER_1);
    }

    @Override
    public List<ItemOrderDTO> prepareListDTO() {
        return Arrays.asList(new ItemOrderDTO(ID_1, ITEM_1, ORDER_1), new ItemOrderDTO(ID_2, ITEM_2, ORDER_2));
    }

    @Override
    public List<ItemOrderEntity> prepareListEntity() {

        return Arrays.asList(new ItemOrderEntity(ID_1, ITEM_1, ORDER_1), new ItemOrderEntity(ID_2, ITEM_2, ORDER_2));
    }

    @Override
    public ModelMapperConverter getConverter() {
        return itemOrderConverter;
    }

    @Override
    public List<Function<ItemOrderDTO, ItemOrderDTO>> getFunctions() {
        Function<ItemOrderDTO, ItemOrderDTO> decreasedItemId = (dto) -> {
            dto.setItemId(dto.getItemId() - 5L);
            return dto;
        };
        Function<ItemOrderDTO, ItemOrderDTO> multipledItemId = dto -> {
            dto.setItemId(dto.getItemId() * 3L);
            return dto;
        };
        return Arrays.asList(decreasedItemId, multipledItemId);
    }

    @Override
    public ItemOrderDTO getConvertedAfterAdditionalFunctions() {
        ItemOrderDTO dto = prepareDTO();
        dto.setItemId((dto.getItemId() - 5L) * 3L);
        return dto;
    }
}