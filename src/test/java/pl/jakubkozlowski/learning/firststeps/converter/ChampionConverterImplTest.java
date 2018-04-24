package pl.jakubkozlowski.learning.firststeps.converter;

import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.learning.firststeps.dto.ChampionDTO;
import pl.jakubkozlowski.learning.firststeps.model.ChampionEntity;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class ChampionConverterImplTest extends ModelMapperConverterTest<ChampionDTO, ChampionEntity> {

    @Autowired
    private ChampionConverter championConverter;

    @TestConfiguration
    static class ChampionConverterImplTestContextConfiguration {

        @Autowired
        private ModelMapper modelMapper;

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public ChampionConverter championConverter() {
            return new ChampionConverterImpl(modelMapper);
        }
    }

    @Override
    public ChampionDTO prepareDTO() {
        return new ChampionDTO();
    }

    @Override
    public ChampionEntity prepareEntity() {
        return new ChampionEntity();
    }

    @Override
    public List<ChampionDTO> prepareListDTO() {
        return Arrays.asList(new ChampionDTO(), new ChampionDTO());
    }

    @Override
    public List<ChampionEntity> prepareListEntity() {

        return Arrays.asList(new ChampionEntity(), new ChampionEntity());
    }

    @Override
    public ModelMapperConverter<ChampionDTO, ChampionEntity> getConverter() {
        return championConverter;
    }
}