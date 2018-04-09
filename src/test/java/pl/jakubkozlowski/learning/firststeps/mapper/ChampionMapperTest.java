package pl.jakubkozlowski.learning.firststeps.mapper;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.learning.firststeps.model.Champion;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@MybatisTest
//For autoincrement reset between tests
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ChampionMapperTest {

    private static final String AATROX = "Aatrox";
    private static final String AHRI = "Ahri";

    @Autowired
    private ChampionMapper championMapper;
    private Champion expectedAatrox;
    private Champion expectedAhri;


    @Before
    public void setUp() throws Exception {
        expectedAatrox = new Champion(1L, AATROX);
        expectedAhri = new Champion(2L, AHRI);
    }

    @Test
    public void whenFindOne_thenReturnChampion() {
        //given
        championMapper.persist(expectedAatrox);
        //when
        Champion actual = championMapper.findById(1L);
        //then
        assertThat(actual)
                .isEqualTo(expectedAatrox);
    }

    @Test
    public void whenFindAll_thenReturnChampionList(){
        //given
        championMapper.persist(expectedAatrox);
        championMapper.persist(expectedAhri);
        //when
        List<Champion> actual = championMapper.findAll();
        //then
        assertThat(actual.get(0))
                .isEqualTo(expectedAatrox);
        assertThat(actual.get(1))
                .isEqualTo(expectedAhri);
    }

    @Test
    public void whenPersist_thenReturnChampion(){
        //when
        championMapper.persist(expectedAhri);
        //then
        assertThat(championMapper.findByName(expectedAhri.getName()))
                .isEqualTo(new Champion(1L, AHRI));

    }

    @Test(expected = DuplicateKeyException.class)
    public void whenPersistWithTheSameName_thenExceptionOccur(){
        //when
        championMapper.persist(expectedAhri);
        championMapper.persist(expectedAhri);
        //then

    }

    @Test
    public void whenUpdate_thenUpdateChampionEntity(){
        //given
        championMapper.persist(expectedAatrox);
        //when
        championMapper.update(1L, expectedAhri);
        Champion prev = championMapper.findById(1L);
        Champion actual = championMapper.findById(2L);
        //then
        assertThat(prev).isNull();
        assertThat(actual)
                .isEqualTo(expectedAhri);

    }

    @Test(expected = DuplicateKeyException.class)
    public void whenUpdateToUsedId_thenExceptionOccurs(){
        //given
        championMapper.persist(expectedAatrox);
        championMapper.persist(expectedAhri);
        //when
        championMapper.update(1L, new Champion(2L, "Mark"));
        //then
        //DuplicateKeyException occurs
    }

    @Test(expected = DuplicateKeyException.class)
    public void whenUpdateToUsedName_thenExceptionOccurs(){
        //given
        championMapper.persist(expectedAatrox);
        championMapper.persist(expectedAhri);
        //when
        championMapper.update(1L, new Champion(1L, "Ahri"));
        //then
        //DuplicateKeyException occurs
    }

    @Test
    public void whenDeleteById_theDeleteChampion(){
        //given
        championMapper.persist(expectedAatrox);
        Champion previous = championMapper.findById(1L);
        assertThat(previous)
                .isEqualTo(expectedAatrox);
        //when
        championMapper.deleteById(1L);
        Champion actual = championMapper.findById(1L);
        //then
        assertThat(actual).isNull();
    }

}