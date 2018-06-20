package pl.jakubkozlowski.leagueoflegends.restAPI.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.leagueoflegends.restAPI.converter.ChampionConverter;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ChampionDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.mapper.ChampionMapper;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ChampionEntity;
import pl.jakubkozlowski.leagueoflegends.restAPI.shared.Page;
import pl.jakubkozlowski.leagueoflegends.restAPI.shared.Pageable;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jakubkozlowski.leagueoflegends.restAPI.descriptor.ChampionTestConstants.*;

@RunWith(SpringRunner.class)
//@SpringBootTest ->1st solution- to Integration Testing. All @Beans are injected
public class ChampionServiceImplTest {

    @Autowired
    private ChampionService championService;

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


    @Autowired
    private ChampionMapper championMapper;

    @Autowired
    private ChampionConverter championConverter;


    private Pageable pageable;

    private ChampionEntity championEntityAatrox;
    private ChampionEntity championEntityAhri;
    private ChampionEntity championEntityAnivia;

    private ChampionDTO championDTOAatrox;
    private ChampionDTO championDTOAhri;
    private ChampionDTO championDTOAnivia;
    private Page<ChampionEntity> championEntityPage;
    private Page<ChampionDTO> championDTOPage;

    @Before
    public void setUp() throws Exception {

        championEntityAatrox = new ChampionEntity(ID_1, AATROX);
        championEntityAhri = new ChampionEntity(ID_2, AHRI);
        championEntityAnivia = new ChampionEntity(ID_3, ANIVIA);

        championDTOAatrox = new ChampionDTO(ID_1, AATROX);
        championDTOAhri = new ChampionDTO(ID_2, AHRI);
        championDTOAnivia = new ChampionDTO(ID_3, ANIVIA);

        pageable = new Pageable(PAGE_0, SIZE_1);
        championEntityPage = new Page<>(Arrays.asList(championEntityAatrox, championEntityAhri, championEntityAnivia), PAGE_0, TOTAL_COUNT_3);
        championDTOPage = new Page<>(Arrays.asList(championDTOAatrox, championDTOAhri, championDTOAnivia), PAGE_0, TOTAL_COUNT_3);

        Mockito.when(championMapper.findById(championEntityAatrox.getId()))
                .thenReturn(championEntityAatrox);
        Mockito.when(championMapper.findPage(pageable))
                .thenReturn(championEntityPage);
        Mockito.when(championConverter.convertEntity(championEntityAatrox))
                .thenReturn(championDTOAatrox);
        Mockito.when(championConverter.convertDTO(championDTOAatrox))
                .thenReturn(championEntityAatrox);
        Mockito.when(championConverter.convertPageEntity(championEntityPage))
                .thenReturn(championDTOPage);
    }

    @Test
    public void whenFindPage_thenReturnChampionDTOPage() {
        //when
        Page<ChampionDTO> expectedChampionDTOPage = championService.findPage(pageable).get();
        //then
        assertThat(expectedChampionDTOPage)
                .isEqualTo(championDTOPage);

    }

    @Test
    public void whenFindById_thenReturnChampionDTO() {
        //when
        ChampionDTO actual = championService.findById(championEntityAatrox.getId()).get();
        //then
        assertThat(actual)
                .isEqualTo(championDTOAatrox);
    }

    @Test
    public void whenPersist_thenMethodInvokedWithGivenParameter() {
        //when
        ChampionDTO actual = championService.save(championDTOAatrox);
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