package pl.jakubkozlowski.learning.firststeps.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.learning.firststeps.DTO.ChampionDTO;
import pl.jakubkozlowski.learning.firststeps.converter.ChampionConverter;
import pl.jakubkozlowski.learning.firststeps.converter.ChampionConverterImpl;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jakubkozlowski.learning.firststeps.descriptor.TestDescriptor.*;

@RunWith(SpringRunner.class)
public class ChampionConverterImplTest {

    @Autowired
    private ChampionConverter championConverter;

    @TestConfiguration
    static class EmployeeConverterImplTestContextConfiguration {

        @Bean
        public ChampionConverter championConverter() {
            return new ChampionConverterImpl();
        }
    }

    private ChampionEntity championEntityAatrox;
    private ChampionEntity championEntityAhri;
    private ChampionEntity championEntityAnivia;

    private ChampionDTO championDTOAatrox;
    private ChampionDTO championDTOAhri;
    private ChampionDTO championDTOAnivia;

    private List<ChampionEntity> championEntityList;
    private List<ChampionDTO> championDTOList;

    @Before
    public void setUp() throws Exception {
        createNewChampionEntities();
        createNewChampionDTOs();
        createNewChampionEntityList();
        createNewChampionDTOList();
    }

    private void createNewChampionEntities() {
        championEntityAatrox = new ChampionEntity(ID_1, AATROX);
        championEntityAhri = new ChampionEntity(ID_2, AHRI);
        championEntityAnivia = new ChampionEntity(ID_3, ANIVIA);
    }

    private void createNewChampionDTOs() {
        championDTOAatrox = new ChampionDTO(ID_1, AATROX);
        championDTOAhri = new ChampionDTO(ID_2, AHRI);
        championDTOAnivia = new ChampionDTO(ID_3, ANIVIA);
    }

    private void createNewChampionEntityList() {
        championEntityList = Arrays.asList(championEntityAatrox, championEntityAhri, championEntityAnivia);
    }

    private void createNewChampionDTOList() {
        championDTOList = Arrays.asList(championDTOAatrox, championDTOAhri, championDTOAnivia);
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