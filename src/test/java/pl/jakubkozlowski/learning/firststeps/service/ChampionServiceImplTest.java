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
import pl.jakubkozlowski.learning.firststeps.converter.ChampionConverter;
import pl.jakubkozlowski.learning.firststeps.dto.ChampionDTO;
import pl.jakubkozlowski.learning.firststeps.mapper.ChampionMapper;
import pl.jakubkozlowski.learning.firststeps.model.ChampionEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jakubkozlowski.learning.firststeps.descriptor.ChampionTestConstants.*;

@RunWith(SpringRunner.class)
//@SpringBootTest ->1st solution- to Integration Testing. All @Beans are injected
public class ChampionServiceImplTest {

    @Autowired
    private ChampionService championService;

    @Autowired
    private ChampionMapper championMapper;

    @Autowired
    private ChampionConverter championConverter;

    private void createNewChampionDTOs() {
        championDTOAatrox = new ChampionDTO(ID_1, AATROX);
        championDTOAhri = new ChampionDTO(ID_2, AHRI);
        championDTOAnivia = new ChampionDTO(ID_3, ANIVIA);
    }

    private void createNewChampionEntities() {
        championEntityAatrox = new ChampionEntity(ID_1, AATROX);
        championEntityAhri = new ChampionEntity(ID_2, AHRI);
        championEntityAnivia = new ChampionEntity(ID_3, ANIVIA);
    }

    private ChampionEntity championEntityAatrox;
    private ChampionEntity championEntityAhri;
    private ChampionEntity championEntityAnivia;

    private ChampionDTO championDTOAatrox;
    private ChampionDTO championDTOAhri;
    private ChampionDTO championDTOAnivia;

    private List<ChampionEntity> championEntityList;
    private List<ChampionDTO> championDTOList;

    private void createNewChampionEntityList() {
        championEntityList = new ArrayList<>(3);
        championEntityList = Arrays.asList(championEntityAatrox, championEntityAhri, championEntityAnivia);
    }

    private void createNewChampionDTOList() {
        championDTOList = new ArrayList<>(3);
        championDTOList = Arrays.asList(championDTOAatrox, championDTOAhri, championDTOAnivia);
    }

    @TestConfiguration
    static class ChampionServiceImplTestContextConfiguration {

        @MockBean
        private ChampionMapper championMapper;

        @MockBean
        private ChampionConverter championConverter;

        @Bean
        public ChampionService championService() {
            return new ChampionServiceImpl(championMapper, championConverter);
        }

    }

    @Before
    public void setUp() throws Exception {
        createNewChampionEntities();
        createNewChampionDTOs();
        createNewChampionEntityList();
        createNewChampionDTOList();

        Mockito.when(championMapper.findById(championEntityAatrox.getId()))
                .thenReturn(championEntityAatrox);
        Mockito.when(championMapper.findAll())
                .thenReturn(championEntityList);
        Mockito.when(championConverter.convert(championEntityAatrox))
                .thenReturn(championDTOAatrox);
        Mockito.when(championConverter.convert(championDTOAatrox))
                .thenReturn(championEntityAatrox);
        Mockito.when(championConverter.convertListDTO(championDTOList))
                .thenReturn(championEntityList);
        Mockito.when(championConverter.convertListEntity(championEntityList))
                .thenReturn(championDTOList);

    }

    @Test
    public void whenPersist_thenMethodInvokedWithGivenParameter() {
        //when
        championService.save(championDTOAatrox);
        //then
        Mockito.verify(championMapper, Mockito.times(1)).save(championEntityAatrox);
    }


    @Test
    public void whenFindAll_thenReturnChampionDTOList() {
        //when
        List<ChampionDTO> expectedChampionDTOList = championService.findAll();
        //then
        assertThat(expectedChampionDTOList.size())
                .isEqualTo(3);
        assertThat(expectedChampionDTOList)
                .isEqualTo(championDTOList);

    }


    @Test
    public void whenFindById_thenReturnChampionDTO() {
        //when
        ChampionDTO actual = championService.findById(championEntityAatrox.getId());
        //then
        assertThat(actual)
                .isEqualTo(championDTOAatrox);
    }

    @Test
    public void whenUpdate_thenMethodInvokedWithExpectedParameter() {
        //when
        championService.update(1L, championDTOAatrox);
        //then
        Mockito.verify(championMapper, Mockito.times(1)).update(ID_1, championEntityAatrox);
    }

    @Test
    public void whenDeleteById_thenMethodInvokedWithExpectedParameter() {
        //when
        championService.deleteById(1L);
        //then
        Mockito.verify(championMapper, Mockito.times(1)).deleteById(ID_1);
    }
}