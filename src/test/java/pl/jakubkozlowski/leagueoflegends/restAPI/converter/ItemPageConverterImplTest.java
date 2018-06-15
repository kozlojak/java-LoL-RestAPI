package pl.jakubkozlowski.leagueoflegends.restAPI.converter;

import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ItemPageDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ItemPageEntity;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static pl.jakubkozlowski.learning.firststeps.descriptor.ItemPageTestConstans.*;

@RunWith(SpringRunner.class)
public class ItemPageConverterImplTest extends ModelMapperConverterTest<ItemPageDTO, ItemPageEntity> {

    @Autowired
    private ItemPageConverter itemPageConverter;

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

    @Override
    public ItemPageDTO prepareDTO() {
        return new ItemPageDTO(ID_1, ATTACK, ATTACK_DSC, ATTACK_CHAMPION_ID, ATTACK_ROLE_ID, ATTACK_USER_ID);
    }

    @Override
    public ItemPageEntity prepareEntity() {
        return new ItemPageEntity(ID_1, ATTACK, ATTACK_DSC, ATTACK_CHAMPION_ID, ATTACK_ROLE_ID, ATTACK_USER_ID);
    }

    @Override
    public List<ItemPageDTO> prepareListDTO() {
        return Arrays.asList(new ItemPageDTO(ID_1, ATTACK, ATTACK_DSC, ATTACK_CHAMPION_ID, ATTACK_ROLE_ID, ATTACK_USER_ID), new ItemPageDTO(ID_2, DEFENCE, DEFENCE_DSC, DEFENCE_CHAMPION_ID, DEFENCE_ROLE_ID, DEFENCE_USER_ID));
    }

    @Override
    public List<ItemPageEntity> prepareListEntity() {

        return Arrays.asList(new ItemPageEntity(ID_1, ATTACK, ATTACK_DSC, ATTACK_CHAMPION_ID, ATTACK_ROLE_ID, ATTACK_USER_ID), new ItemPageEntity(ID_2, DEFENCE, DEFENCE_DSC, DEFENCE_CHAMPION_ID, DEFENCE_ROLE_ID, DEFENCE_USER_ID));
    }

    @Override
    public ModelMapperConverter getConverter() {
        return itemPageConverter;
    }

    @Override
    public List<Function<ItemPageDTO, ItemPageDTO>> getFunctions() {
        Function<ItemPageDTO, ItemPageDTO> removeLettersAFromName = (dto) -> {
            dto.setDescription(dto.getDescription().replace("A", ""));
            return dto;
        };
        Function<ItemPageDTO, ItemPageDTO> addXYZToNameEnd = dto -> {
            dto.setDescription(dto.getDescription() + "XYZ");
            return dto;
        };
        return Arrays.asList(removeLettersAFromName, addXYZToNameEnd);
    }

    @Override
    public ItemPageDTO getConvertedAfterAdditionalFunctions() {
        ItemPageDTO dto = prepareDTO();
        dto.setDescription(dto.getDescription().replace("A", ""));
        dto.setDescription(dto.getDescription() + "XYZ");
        return dto;
    }
}
