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
import pl.jakubkozlowski.learning.firststeps.converter.ItemPageConverter;
import pl.jakubkozlowski.learning.firststeps.dto.ItemPageDTO;
import pl.jakubkozlowski.learning.firststeps.mapper.ItemPageMapper;
import pl.jakubkozlowski.learning.firststeps.model.ItemPageEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static pl.jakubkozlowski.learning.firststeps.descriptor.ItemPageTestConstans.*;


@RunWith(SpringRunner.class)
public class ItemPageServiceImplTest {

    @Autowired
    private ItemPageService itemPageService;

    @Autowired
    private ItemPageMapper itemPageMapper;

    @Autowired
    private ItemPageConverter itemPageConverter;

    @Test
    public void whenFindAll_thenReturnListOfItemPagesDTO() {
        //when
        List<ItemPageDTO> actual = itemPageService.findAll().get();
        //then
        assertThat(actual)
                .isEqualTo(itemPageDTOList);
    }

    private ItemPageEntity itemPageEntityAttack;
    private ItemPageDTO itemPageDTOAttack;
    private ItemPageEntity itemPageEntityDefence;
    private ItemPageDTO itemPageDTODefence;
    private List<ItemPageEntity> itemPageEntityList;
    private List<ItemPageDTO> itemPageDTOList;

    @Before
    public void setUp() throws Exception {
        itemPageEntityAttack = new ItemPageEntity(ID_1, ATTACK, ATTACK_DSC, ATTACK_CHAMPION_ID, ATTACK_ROLE_ID, ATTACK_USER_ID);
        itemPageDTOAttack = new ItemPageDTO(ID_1, ATTACK, ATTACK_DSC, ATTACK_CHAMPION_ID, ATTACK_ROLE_ID, ATTACK_USER_ID);
        itemPageEntityDefence = new ItemPageEntity(ID_2, DEFENCE, DEFENCE_DSC, DEFENCE_CHAMPION_ID, DEFENCE_ROLE_ID, DEFENCE_USER_ID);
        itemPageDTODefence = new ItemPageDTO(ID_2, DEFENCE, DEFENCE_DSC, DEFENCE_CHAMPION_ID, DEFENCE_ROLE_ID, DEFENCE_USER_ID);
        itemPageEntityList = Arrays.asList(itemPageEntityAttack, itemPageEntityDefence);
        itemPageDTOList = Arrays.asList(itemPageDTOAttack, itemPageDTODefence);

        Mockito.when(itemPageMapper.findAll())
                .thenReturn(itemPageEntityList);
        Mockito.when(itemPageMapper.findById(itemPageEntityAttack.getId()))
                .thenReturn(itemPageEntityAttack);
        Mockito.when(itemPageMapper.findByPagename(itemPageEntityAttack.getPagename()))
                .thenReturn(itemPageEntityAttack);
        Mockito.when(itemPageConverter.convertDTO(itemPageDTOAttack))
                .thenReturn(itemPageEntityAttack);
        Mockito.when(itemPageConverter.convertEntity(itemPageEntityAttack))
                .thenReturn(itemPageDTOAttack);
        Mockito.when(itemPageConverter.convertListDTO(itemPageDTOList))
                .thenReturn(itemPageEntityList);
        Mockito.when(itemPageConverter.convertListEntity(itemPageEntityList))
                .thenReturn(itemPageDTOList);
    }

    @Test
    public void whenFindById_thenReturnItemPageDTO() {
        //when
        ItemPageDTO actual = itemPageService.findById(itemPageEntityAttack.getId()).get();
        //then
        assertThat(actual)
                .isEqualTo(itemPageDTOAttack);
    }

    @Test
    public void whenSave_thenReturnItemPageDTO() {
        //when
        ItemPageDTO actual = itemPageService.save(itemPageDTOAttack);
        //then
        assertThat(actual)
                .isEqualTo(itemPageDTOAttack);
    }

    @Test
    public void whenFindByPagename_thenReturnUncompletedItemPageDTO() {
        //when
        ItemPageDTO actual = itemPageService.findByPagename(itemPageEntityAttack.getPagename()).get();
        //then
        assertThat(actual)
                .isEqualTo(itemPageDTOAttack);
    }

    @TestConfiguration
    static class ItemPageServiceImplTestContextConfiguration {

        @MockBean
        private ItemPageMapper itemPageMapper;

        @MockBean
        private ItemPageConverter itemPageConverter;

        @Bean
        public ItemPageService itemPageService() {
            return new ItemPageServiceImpl(itemPageMapper, itemPageConverter);
        }

    }

    @Test
    public void whenUpdate_thenReturnUpdatedItemPageDTO() {
        //when
        itemPageService.update(ID_1, itemPageDTOAttack);
        //then
        Mockito.verify(itemPageMapper, Mockito.times(1)).update(ID_1, itemPageEntityAttack);
    }

    @Test
    public void whenDeleteById_thenDeleteItemPageDTO() {
        //when
        itemPageService.deleteById(ID_1);
        //then
        Mockito.verify(itemPageMapper, Mockito.times(1)).deleteById(ID_1);
    }
}