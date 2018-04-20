package pl.jakubkozlowski.learning.firststeps.converter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.learning.firststeps.dto.ItemPageDTO;
import pl.jakubkozlowski.learning.firststeps.model.ItemPageEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jakubkozlowski.learning.firststeps.descriptor.ItemPageTestConstans.*;

@RunWith(SpringRunner.class)
public class ItemPageConverterImplTest {

    @Autowired
    private ItemPageConverter itemPageConverter;
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
    }

    @Test
    public void whenConvertUserEntity_thenReturnUserDTO() {
        //when
        ItemPageDTO actual = itemPageConverter.convert(itemPageEntityAttack);
        //then
        assertThat(actual)
                .isEqualTo(itemPageDTOAttack);
    }

    @Test
    public void whenConvertChampionDTO_thenReturnChampionEntity() {
        //when
        ItemPageEntity actual = itemPageConverter.convert(itemPageDTOAttack);
        //then
        assertThat(actual)
                .isEqualTo(itemPageEntityAttack);
    }

    @Test
    public void whenConvertChampionListEntity_thenReturnChampionListDTO() {
        //when
        List<ItemPageDTO> actual = itemPageConverter.convertListEntity(itemPageEntityList);
        //then
        assertThat(actual)
                .isEqualTo(itemPageDTOList);
    }

    @Test
    public void whenConvertChampionListDTO_thenReturnChampionListEntity() {
        //when
        List<ItemPageEntity> actual = itemPageConverter.convertListDTO(itemPageDTOList);
        //then
        assertThat(actual)
                .isEqualTo(itemPageEntityList);
    }

    @TestConfiguration
    static class ItemPageConverterImplTestContextConfiguration {

        @Autowired
        private ModelMapper modelMapper;

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public ItemPageConverter itemPageConverter() {
            return new ItemPageConverterImpl(modelMapper);
        }
    }
}
