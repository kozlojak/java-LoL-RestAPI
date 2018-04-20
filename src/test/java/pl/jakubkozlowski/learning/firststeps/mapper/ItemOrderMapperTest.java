package pl.jakubkozlowski.learning.firststeps.mapper;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.learning.firststeps.model.ItemOrderEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.jakubkozlowski.learning.firststeps.descriptor.ItemOrderTestConstants.*;

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
        expectedSet1 = new ItemOrderEntity(ID_1, ITEM_1, ORDER_1);
        expectedSet2 = new ItemOrderEntity(ID_2, ITEM_2, ORDER_2);
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
        ItemOrderEntity actual = itemOrderMapper.findById(ID_1);
        //then
        assertThat(actual)
                .isEqualTo(expectedSet1);
    }

    @Test
    public void whenUpdate_thenReturnUpdatedItemOrder() {
        //given
        itemOrderMapper.save(expectedSet1);
        //when
        itemOrderMapper.update(ID_1, expectedSet2);
        ItemOrderEntity actual = itemOrderMapper.findById(ID_2);
        //then
        assertThat(actual)
                .isEqualTo(expectedSet2);
    }

    @Test
    public void whenDeleteByItemPageId_thenDeleteItemOrder() {
        //given
        itemOrderMapper.save(expectedSet1);
        //when
        itemOrderMapper.deleteById(ID_1);
        ItemOrderEntity actual = itemOrderMapper.findById(ID_1);
        //then
        assertThat(actual)
                .isNull();
    }
}