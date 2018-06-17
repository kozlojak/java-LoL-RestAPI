package pl.jakubkozlowski.leagueoflegends.restAPI.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ChampionEntity;
import pl.jakubkozlowski.leagueoflegends.restAPI.shared.Page;
import pl.jakubkozlowski.leagueoflegends.restAPI.shared.Pageable;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jakubkozlowski.leagueoflegends.restAPI.descriptor.ChampionTestConstants.*;

@RunWith(SpringRunner.class)
@MybatisTest
//For autoincrement reset between tests
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ChampionMapperTest {

    @Autowired
    private ChampionMapper championMapper;
    private ChampionEntity expectedAatrox;
    private ChampionEntity expectedAhri;
    private Pageable pageable;
    private Page<ChampionEntity> page;

    @Before
    public void setUp() {
        expectedAatrox = new ChampionEntity(ID_1, AATROX);
        expectedAhri = new ChampionEntity(ID_2, AHRI);
        pageable = new Pageable(PAGE_0, SIZE_2);
        page = new Page<>(Arrays.asList(expectedAatrox, expectedAhri), PAGE_0, TOTAL_COUNT_2);
    }


    @Test
    public void whenFindOne_thenReturnChampion() {
        //given
        championMapper.save(expectedAatrox);
        //when
        ChampionEntity actual = championMapper.findById(ID_1);
        //then
        assertThat(actual)
                .isEqualTo(expectedAatrox);
    }

    @Test
    public void whenFindPage_thenReturnPageOfChampions() {
        //given
        championMapper.save(expectedAatrox);
        championMapper.save(expectedAhri);
        //when
        Page<ChampionEntity> actual = championMapper.findPage(pageable);
        //then
        assertThat(actual)
                .isEqualTo(page);
    }

    @Test
    public void whenPersist_thenIdAutoincrementsAndReturnsChampion() {
        //when
        championMapper.save(expectedAhri);
        //then
        assertThat(championMapper.findByName(expectedAhri.getName()))
                .isEqualTo(new ChampionEntity(ID_1, AHRI));

    }

    @Test(expected = DuplicateKeyException.class)
    public void whenPersistWithTheSameName_thenExceptionOccur() {
        //when
        championMapper.save(expectedAhri);
        championMapper.save(expectedAhri);
        //then

    }

    @Test
    public void whenUpdate_thenUpdateChampionEntity() {
        //given
        championMapper.save(expectedAatrox);
        //when
        championMapper.update(ID_1, expectedAhri);
        ChampionEntity prev = championMapper.findById(ID_1);
        ChampionEntity actual = championMapper.findById(ID_2);
        //then
        assertThat(prev).isNull();
        assertThat(actual)
                .isEqualTo(expectedAhri);

    }

    @Test(expected = DuplicateKeyException.class)
    public void whenUpdateToUsedName_thenExceptionOccurs() {
        //given
        championMapper.save(expectedAatrox);
        championMapper.save(expectedAhri);
        //when
        championMapper.update(ID_1, new ChampionEntity(ID_1, AHRI));
        //then
        //DuplicateKeyException throws
    }

    @Test
    public void whenDeleteById_theDeleteChampion() {
        //given
        championMapper.save(expectedAatrox);
        ChampionEntity previous = championMapper.findById(ID_1);
        assertThat(previous)
                .isEqualTo(expectedAatrox);
        //when
        championMapper.deleteById(ID_1);
        ChampionEntity actual = championMapper.findById(ID_1);
        //then
        assertThat(actual).isNull();
    }

}