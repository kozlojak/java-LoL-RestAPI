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
import java.util.function.Function;

import static pl.jakubkozlowski.learning.firststeps.descriptor.ChampionTestConstants.*;

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
        return new ChampionDTO(ID_1, AATROX);
    }

    @Override
    public ChampionEntity prepareEntity() {
        return new ChampionEntity(ID_1, AATROX);
    }

    @Override
    public List<ChampionDTO> prepareListDTO() {
        return Arrays.asList(new ChampionDTO(ID_1, AATROX), new ChampionDTO(ID_2, ANIVIA));
    }

    @Override
    public List<ChampionEntity> prepareListEntity() {

        return Arrays.asList(new ChampionEntity(ID_1, AATROX), new ChampionEntity(ID_2, ANIVIA));
    }

    @Override
    public ModelMapperConverter<ChampionDTO, ChampionEntity> getConverter() {
        return championConverter;
    }

    @Override
    public List<Function<ChampionDTO, ChampionDTO>> getFunctions() {
        Function<ChampionDTO, ChampionDTO> removeLettersAFromName = (dto) -> {
            dto.setName(dto.getName().replace("A", ""));
            return dto;
        };
        Function<ChampionDTO, ChampionDTO> addXYZToNameEnd = dto -> {
            dto.setName(dto.getName() + "XYZ");
            return dto;
        };
        return Arrays.asList(removeLettersAFromName, addXYZToNameEnd);
    }

    @Override
    public ChampionDTO getConvertedAfterAdditionalFunctions() {
        ChampionDTO dto = prepareDTO();
        dto.setName(dto.getName().replace("A", ""));
        dto.setName(dto.getName() + "XYZ");
        return dto;
    }
}