package pl.jakubkozlowski.leagueoflegends.restAPI.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.leagueoflegends.restAPI.descriptor.ItemPageTestConstans;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ItemPageEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        expectedAttack = new ItemPageEntity(ItemPageTestConstans.ID_1, ItemPageTestConstans.ATTACK, ItemPageTestConstans.ATTACK_DSC, ItemPageTestConstans.ATTACK_CHAMPION_ID, ItemPageTestConstans.ATTACK_ROLE_ID, ItemPageTestConstans.ATTACK_USER_ID);
        expectedDefence = new ItemPageEntity(ItemPageTestConstans.ID_2, ItemPageTestConstans.DEFENCE, ItemPageTestConstans.DEFENCE_DSC, ItemPageTestConstans.DEFENCE_CHAMPION_ID, ItemPageTestConstans.DEFENCE_ROLE_ID, ItemPageTestConstans.DEFENCE_USER_ID);
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
        ItemPageEntity actual = itemPageMapper.findById(ItemPageTestConstans.ID_1);
        //then
        assertThat(actual)
                .isEqualTo(expectedAttack);
    }

    @Test
    public void whenFindItemPageByName_thenReturnUncompletedItemPage() {
        //given
        itemPageMapper.save(expectedAttack);
        //when
        ItemPageEntity actual = itemPageMapper.findByPagename(ItemPageTestConstans.ATTACK);
        //then
        assertThat(actual)
                .isEqualTo(expectedAttack);
    }

    @Test
    public void whenUpdateUser_thenReturnUpdatedItemPage() {
        //given
        itemPageMapper.save(expectedAttack);
        //when
        itemPageMapper.update(ItemPageTestConstans.ID_1, expectedDefence);
        ItemPageEntity actual = itemPageMapper.findById(ItemPageTestConstans.ID_1);
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
        itemPageMapper.deleteById(ItemPageTestConstans.ID_1);
        ItemPageEntity actual = itemPageMapper.findById(ItemPageTestConstans.ID_1);
        //then
        assertThat(actual)
                .isNull();
    }
}