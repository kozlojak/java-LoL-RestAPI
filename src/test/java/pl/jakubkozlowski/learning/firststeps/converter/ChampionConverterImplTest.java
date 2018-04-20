package pl.jakubkozlowski.learning.firststeps.converter;

import org.junit.Before;
import org.junit.Test;
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

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jakubkozlowski.learning.firststeps.descriptor.ChampionTestConstants.*;

@RunWith(SpringRunner.class)
public class ChampionConverterImplTest {

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

    private ChampionEntity championEntityAatrox;
    private ChampionEntity championEntityAhri;
    private ChampionDTO championDTOAatrox;
    private ChampionDTO championDTOAhri;
    private List<ChampionEntity> championEntityList;
    private List<ChampionDTO> championDTOList;

    @Before
    public void setUp() throws Exception {
        championEntityAatrox = new ChampionEntity(ID_1, AATROX);
        championEntityAhri = new ChampionEntity(ID_2, AHRI);
        championDTOAatrox = new ChampionDTO(ID_1, AATROX);
        championDTOAhri = new ChampionDTO(ID_2, AHRI);
        championEntityList = Arrays.asList(championEntityAatrox, championEntityAhri);
        championDTOList = Arrays.asList(championDTOAatrox, championDTOAhri);
    }

    @Test
    public void whenConvertChampionEntity_thenReturnChampionDTO() {
        //when
        ChampionDTO actual = championConverter.convert(championEntityAatrox);
        //then
        assertThat(actual)
                .isEqualTo(championDTOAatrox);
    }

    @Test
    public void whenConvertChampionDTO_thenReturnChampionEntity() {
        //when
        ChampionEntity actual = championConverter.convert(championDTOAatrox);
        //then
        assertThat(actual)
                .isEqualTo(championEntityAatrox);
    }

    @Test
    public void whenConvertChampionListEntity_thenReturnChampionListDTO() {
        //when
        List<ChampionDTO> actual = championConverter.convertListEntity(championEntityList);
        //then
        assertThat(actual)
                .isEqualTo(championDTOList);
    }

    @Test
    public void whenConvertChampionListDTO_thenReturnChampionListEntity() {
        //when
        List<ChampionEntity> actual = championConverter.convertListDTO(championDTOList);
        //then
        assertThat(actual)
                .isEqualTo(championEntityList);
    }
}