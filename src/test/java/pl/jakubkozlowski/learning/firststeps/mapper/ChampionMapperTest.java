package pl.jakubkozlowski.learning.firststeps.mapper;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.learning.firststeps.model.ChampionEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jakubkozlowski.learning.firststeps.descriptor.TestDescriptor.*;

@RunWith(SpringRunner.class)
@MybatisTest
//For autoincrement reset between tests
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ChampionMapperTest {

    @Autowired
    private ChampionMapper championMapper;
    private ChampionEntity expectedAatrox;
    private ChampionEntity expectedAhri;


    @Before
    public void setUp() throws Exception {
        expectedAatrox = new ChampionEntity(ID_1, AATROX);
        expectedAhri = new ChampionEntity(ID_2, AHRI);
    }

    @Test
    public void whenFindOne_thenReturnChampion() {
        //given
        championMapper.persist(expectedAatrox);
        //when
        ChampionEntity actual = championMapper.findById(ID_1);
        //then
        assertThat(actual)
                .isEqualTo(expectedAatrox);
    }

    @Test
    public void whenFindAll_thenReturnChampionList() {
        //given
        championMapper.persist(expectedAatrox);
        championMapper.persist(expectedAhri);
        //when
        List<ChampionEntity> actual = championMapper.findAll();
        //then
        assertThat(actual.size())
                .isEqualTo(2);
        assertThat(actual.get(0))
                .isEqualTo(expectedAatrox);
        assertThat(actual.get(1))
                .isEqualTo(expectedAhri);
    }

    @Test
    public void whenPersist_thenIdAutoincrementsAndReturnsChampion() {
        //when
        championMapper.persist(expectedAhri);
        //then
        assertThat(championMapper.findByName(expectedAhri.getName()))
                .isEqualTo(new ChampionEntity(ID_1, AHRI));

    }

    @Test(expected = DuplicateKeyException.class)
    public void whenPersistWithTheSameName_thenExceptionOccur() {
        //when
        championMapper.persist(expectedAhri);
        championMapper.persist(expectedAhri);
        //then

    }

    @Test
    public void whenUpdate_thenUpdateChampionEntity() {
        //given
        championMapper.persist(expectedAatrox);
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
        championMapper.persist(expectedAatrox);
        championMapper.persist(expectedAhri);
        //when
        championMapper.update(ID_1, new ChampionEntity(ID_1, AHRI));
        //then
        //DuplicateKeyException throws
    }

    @Test
    public void whenDeleteById_theDeleteChampion() {
        //given
        championMapper.persist(expectedAatrox);
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