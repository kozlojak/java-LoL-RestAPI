package pl.jakubkozlowski.leagueoflegends.restAPI.mapper;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.leagueoflegends.restAPI.descriptor.ItemOrderTestConstants;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ItemOrderEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@MybatisTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ItemOrderMapperTest {

    @Autowired
    private ItemOrderMapper itemOrderMapper;

    private ItemOrderEntity expectedSet1;
    private ItemOrderEntity expectedSet2;

    @Before
    public void SetUp() throws Exception {
        expectedSet1 = new ItemOrderEntity(ItemOrderTestConstants.ID_1, ItemOrderTestConstants.ITEM_1, ItemOrderTestConstants.ORDER_1);
        expectedSet2 = new ItemOrderEntity(ItemOrderTestConstants.ID_2, ItemOrderTestConstants.ITEM_2, ItemOrderTestConstants.ORDER_2);
    }

    @Test
    public void whenFindAll_thenReturnListOfItemOrders() {
        //given
        itemOrderMapper.save(expectedSet1);
        itemOrderMapper.save(expectedSet2);
        //when
        List<ItemOrderEntity> actual = itemOrderMapper.findAll();
        //then
        assertThat(actual.size())
                .isEqualTo(2);
        assertThat(actual.get(0))
                .isEqualTo(expectedSet1);
        assertThat(actual.get(1))
                .isEqualTo(expectedSet2);
    }

    @Test
    public void whenFindId_thenReturnItemOrder() {
        //given
        itemOrderMapper.save(expectedSet1);
        //when
        ItemOrderEntity actual = itemOrderMapper.findById(ItemOrderTestConstants.ID_1);
        //then
        assertThat(actual)
                .isEqualTo(expectedSet1);
    }

    @Test
    public void whenUpdate_thenReturnUpdatedItemOrder() {
        //given
        itemOrderMapper.save(expectedSet1);
        //when
        itemOrderMapper.update(ItemOrderTestConstants.ID_1, expectedSet2);
        ItemOrderEntity actual = itemOrderMapper.findById(ItemOrderTestConstants.ID_2);
        //then
        assertThat(actual)
                .isEqualTo(expectedSet2);
    }

    @Test
    public void whenDeleteByItemPageId_thenDeleteItemOrder() {
        //given
        itemOrderMapper.save(expectedSet1);
        //when
        itemOrderMapper.deleteById(ItemOrderTestConstants.ID_1);
        ItemOrderEntity actual = itemOrderMapper.findById(ItemOrderTestConstants.ID_1);
        //then
        assertThat(actual)
                .isNull();
    }
}