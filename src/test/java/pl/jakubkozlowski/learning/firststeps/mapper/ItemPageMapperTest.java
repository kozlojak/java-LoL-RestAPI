package pl.jakubkozlowski.learning.firststeps.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.learning.firststeps.model.ItemPageEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.jakubkozlowski.learning.firststeps.descriptor.ItemPageTestConstans.*;

@RunWith(SpringRunner.class)
@MybatisTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ItemPageMapperTest {

    @Autowired
    private ItemPageMapper itemPageMapper;
    private ItemPageEntity expectedAttack;
    private ItemPageEntity expectedDefence;

    @Before
    public void SetUp() throws Exception {
        expectedAttack = new ItemPageEntity(ID_1, ATTACK, ATTACK_DSC, ATTACK_CHAMPION_ID, ATTACK_ROLE_ID, ATTACK_USER_ID);
        expectedDefence = new ItemPageEntity(ID_2, DEFENCE, DEFENCE_DSC, DEFENCE_CHAMPION_ID, DEFENCE_ROLE_ID, DEFENCE_USER_ID);
    }

    @Test
    public void whenFindAllItemPages_thenReturnListOfItemPages() {
        //given
        itemPageMapper.save(expectedAttack);
        itemPageMapper.save(expectedDefence);
        //when
        List<ItemPageEntity> actual = itemPageMapper.findAll();
        //then
        assertThat(actual.size())
                .isEqualTo(2);
        assertThat(actual.get(0))
                .isEqualTo(expectedAttack);
        assertThat(actual.get(1))
                .isEqualTo(expectedDefence);
    }

    @Test
    public void whenFindItemPageById_thenReturnItemPage() {
        //given
        itemPageMapper.save(expectedAttack);
        //when
        ItemPageEntity actual = itemPageMapper.findById(ID_1);
        //then
        assertThat(actual)
                .isEqualTo(expectedAttack);
    }

    @Test
    public void whenFindItemPageByName_thenReturnUncompletedItemPage() {
        //given
        itemPageMapper.save(expectedAttack);
        //when
        ItemPageEntity actual = itemPageMapper.findByPagename(ATTACK);
        //then
        assertThat(actual)
                .isEqualTo(expectedAttack);
    }

    @Test
    public void whenUpdateUser_thenReturnUpdatedItemPage() {
        //given
        itemPageMapper.save(expectedAttack);
        //when
        itemPageMapper.update(ID_1, expectedDefence);
        ItemPageEntity actual = itemPageMapper.findById(ID_1);
        //then
        assertThat(actual.getPagename())
                .isEqualTo(expectedDefence.getPagename());
        assertThat(actual.getDescription())
                .isEqualTo(expectedDefence.getDescription());
    }

    @Test
    public void whenDeleteById_thenDeleteItemPage() {
        //given
        itemPageMapper.save(expectedAttack);
        //when
        itemPageMapper.deleteById(ID_1);
        ItemPageEntity actual = itemPageMapper.findById(ID_1);
        //then
        assertThat(actual)
                .isNull();
    }
}