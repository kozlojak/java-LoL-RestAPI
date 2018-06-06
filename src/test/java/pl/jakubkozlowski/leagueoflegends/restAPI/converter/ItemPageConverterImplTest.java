package pl.jakubkozlowski.leagueoflegends.restAPI.converter;

import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ItemPageDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ItemPageEntity;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class ItemPageConverterImplTest extends ModelMapperConverterTest<ItemPageDTO, ItemPageEntity> {

    @Autowired
    private ItemPageConverter itemPageConverter;

    @TestConfiguration
    static class ItemPageConverterImplTestContextConfiguration {

        @Autowired
        private ModelMapper modelMapper;

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public ItemPageConverter itemPageConverter() {
            return new ItemPageConverterImpl(modelMapper);
        }
    }

    @Override
    public ItemPageDTO prepareDTO() {
        return new ItemPageDTO();
    }

    @Override
    public ItemPageEntity prepareEntity() {
        return new ItemPageEntity();
    }

    @Override
    public List<ItemPageDTO> prepareListDTO() {
        return Arrays.asList(new ItemPageDTO(), new ItemPageDTO());
    }

    @Override
    public List<ItemPageEntity> prepareListEntity() {

        return Arrays.asList(new ItemPageEntity(), new ItemPageEntity());
    }

    @Override
    public ModelMapperConverter getConverter() {
        return itemPageConverter;
    }
}
