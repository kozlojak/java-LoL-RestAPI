package pl.jakubkozlowski.learning.firststeps.mapper;


import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.learning.firststeps.model.Champion;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@MybatisTest
public class ChampionMapperTest {

    @Autowired
    private ChampionMapper championMapper;
    private Champion expected;
    private Champion expected2;


    @Before
    public void setUp() throws Exception {
        expected = new Champion(1L, "Aatrox");
//        System.out.println(championMapper);
    }

    @Test
    public void whenFindOne_thenReturnChampion() {
        //given
        championMapper.create(expected);
        //when
        Champion actual = championMapper.findOne(1L);
        //then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    public void whenFindAll_thenReturnChampions(){
        //given
        expected2 = new Champion(2L, "Ahri");
        championMapper.create(expected);
        championMapper.create(expected2);
        //when
        List<Champion> actual = championMapper.findAll();
        //then
        assertThat(actual.get(0))
                .isEqualTo(expected);
        assertThat(actual.get(1))
                .isEqualTo(expected2);
    }

    @Test(expected = DuplicateKeyException.class)
    public void whenCreateChampionWithSameId_thenExceptionOccurs(){
        //given
        championMapper.create(expected);
        expected2 = new Champion(1L, "Ahri");
        //when
        championMapper.create(expected2);
        //then
        //DuplicateKeyException occurs
    }

    @Test
    public void whenUpdate_thenUpdateChampionEntity(){
        //given
        championMapper.create(expected);
        expected2 = new Champion(3L, "Ahri");
        //when
        championMapper.update(1L, expected2);
        System.out.println(championMapper.findOne(1L));
        Champion prev = championMapper.findOne(1L);
        Champion actual = championMapper.findOne(3L);
        //then
        assertThat(prev)
                .isEqualTo(null);
        assertThat(actual)
                .isEqualTo(expected2);

    }

    @Test
    public void whenDeleteById_theDeleteChampion(){
        //given
        championMapper.create(expected);
        Champion previous = championMapper.findOne(1L);
        assertThat(previous)
                .isEqualTo(expected);
        //when
        championMapper.deleteById(1L);
        Champion actual = championMapper.findOne(1L);
        //then
        assertThat(actual)
                .isEqualTo(null);
    }

}