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
import pl.jakubkozlowski.learning.firststeps.mapper.ChampionMapper;
import pl.jakubkozlowski.learning.firststeps.model.Champion;
import pl.jakubkozlowski.learning.firststeps.model.ChampionDTO;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@SpringBootTest ->1st solution- to Integration Testing. All @Beans are injected
public class ChampionServiceImplTest {

    private static final String AATROX = "Aatrox";
    private static final String AHRI = "Ahri";
    private static final String ANIVIA = "Anivia";
    public static final Long ID_1 = 1L;
    public static final Long ID_2 = 2L;
    public static final Long ID_3 = 3L;

    @TestConfiguration
    static class ChampionServiceImplTestContextConfiguration {

        @MockBean
        private ChampionMapper championMapper;

        @Bean
        public ChampionService championService() {
            return new ChampionServiceImpl(championMapper);
        }

    }

    @Autowired
    private ChampionService championService;

    @Autowired
    private ChampionMapper championMapper;

    private Champion championAatrox;
    private Champion championAhri;
    private Champion championAnivia;

    private ChampionDTO championDTOAatrox;
    private ChampionDTO championDTOAhri;
    private ChampionDTO championDTOAnivia;

    private List<Champion> championList;
    private List<ChampionDTO> championDTOList;




    @Before
    public void setUp() throws Exception {
        championAatrox = new Champion(ID_1, AATROX);
        championAhri = new Champion(ID_2, AHRI);
        championAnivia = new Champion(ID_3, ANIVIA);

        championDTOAatrox = new ChampionDTO(ID_1, AATROX);
        championDTOAhri = new ChampionDTO(ID_2, AHRI);
        championDTOAnivia = new ChampionDTO(ID_3, ANIVIA);

        championList = new ArrayList<>(3);
        championList.add(championAatrox);
        championList.add(championAhri);
        championList.add(championAnivia);

        championDTOList = new ArrayList<>(3);
        championDTOList.add(championDTOAatrox);
        championDTOList.add(championDTOAhri);
        championDTOList.add(championDTOAnivia);

        Mockito.when(championMapper.findById(championAatrox.getId()))
                .thenReturn(championAatrox);
        Mockito.when(championMapper.findAll())
                .thenReturn(championList);

    }

    @Test
    public void whenFindAll_thenReturnChampionDTOList() {
        //when
        List<ChampionDTO> expectedChampionDTOList= championService.findAll();
        //then
        assertThat(expectedChampionDTOList.size())
                .isEqualTo(3);
        for(int i=0; i<expectedChampionDTOList.size(); i++) {
            assertThat(expectedChampionDTOList.get(i))
                    .isEqualTo(championDTOList.get(i));
        }
    }

    @Test
    public void whenFindById_thenReturnChampionDTO() {
        //when
        ChampionDTO actual = championService.findById(championAatrox.getId());
        //then
        assertThat(actual)
                .isEqualTo(championDTOAatrox);
    }
    @Test
    public void whenPersist_thenMethodInvokedWithGivenParameter(){
        //when
        championService.persist(championDTOAatrox);
        //then
        Mockito.verify(championMapper, Mockito.times(1) ).persist(championAatrox);
    }

    @Test
    public void whenUpdate_thenMethodInvokedWithExpectedParameter(){
        //when
        championService.update(1L, championDTOAatrox);
        //then
        Mockito.verify(championMapper, Mockito.times(1) ).update(1L, championAatrox);
    }

    @Test
    public void whenDeleteById_thenMethodInvokedWithExpectedParameter (){
        //when
        championService.deleteById(1L);
        //then
        Mockito.verify(championMapper, Mockito.times(1) ).deleteById(1L);
    }

}