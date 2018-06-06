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
        return new ItemOrderDTO();
    }

    @Override
    public ItemOrderEntity prepareEntity() {
        return new ItemOrderEntity();
    }

    @Override
    public List<ItemOrderDTO> prepareListDTO() {
        return Arrays.asList(new ItemOrderDTO(), new ItemOrderDTO());
    }

    @Override
    public List<ItemOrderEntity> prepareListEntity() {

        return Arrays.asList(new ItemOrderEntity(), new ItemOrderEntity());
    }

    @Override
    public ModelMapperConverter getConverter() {
        return itemOrderConverter;
    }
}