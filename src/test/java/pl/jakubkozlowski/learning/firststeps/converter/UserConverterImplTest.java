package pl.jakubkozlowski.learning.firststeps.converter;

import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;
import pl.jakubkozlowski.learning.firststeps.model.UserEntity;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static pl.jakubkozlowski.learning.firststeps.descriptor.UserTestConstants.*;

@RunWith(SpringRunner.class)
public class UserConverterImplTest extends ModelMapperConverterTest<UserDTO, UserEntity> {

    @Autowired
    private UserConverter userConverter;

    @TestConfiguration
    static class UserConverterImplTestContextConfiguration {

        @Autowired
        private ModelMapper modelMapper;

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public UserConverter userConverter() {
            return new UserConverterImpl(modelMapper);
        }

    }

    @Override
    public UserDTO prepareDTO() {
        return new UserDTO(ID_1, MARK, MARK_EMAIL, MARK_PASSWORD, MARK_FAV_ROLE_ID, MARK_FAV_CHAMP_ID);
    }

    @Override
    public UserEntity prepareEntity() {
        return new UserEntity(ID_1, MARK, MARK_EMAIL, MARK_PASSWORD, MARK_FAV_ROLE_ID, MARK_FAV_CHAMP_ID);
    }

    @Override
    public List<UserDTO> prepareListDTO() {
        return Arrays.asList(new UserDTO(ID_1, MARK, MARK_EMAIL, MARK_PASSWORD, MARK_FAV_ROLE_ID, MARK_FAV_CHAMP_ID), new UserDTO(ID_2, KATE, KATE_EMAIL, KATE_PASSWORD, KATE_FAV_ROLE_ID, KATE_FAV_CHAMP_ID));
    }

    @Override
    public List<UserEntity> prepareListEntity() {
        return Arrays.asList(new UserEntity(ID_1, MARK, MARK_EMAIL, MARK_PASSWORD, MARK_FAV_ROLE_ID, MARK_FAV_CHAMP_ID), new UserEntity(ID_2, KATE, KATE_EMAIL, KATE_PASSWORD, KATE_FAV_ROLE_ID, KATE_FAV_CHAMP_ID));
    }

    @Override
    public ModelMapperConverter getConverter() {
        return userConverter;
    }

    @Override
    public List<Function<UserDTO, UserDTO>> getFunctions() {
        Function<UserDTO, UserDTO> removeLettersAFromName = (dto) -> {
            dto.setEmail(dto.getEmail().replace(REPLACE_LETTER_A, EMPTY_STRING));
            return dto;
        };
        Function<UserDTO, UserDTO> addXYZToNameEnd = dto -> {
            dto.setEmail(dto.getEmail() + LAST_LETTERS);
            return dto;
        };
        return Arrays.asList(removeLettersAFromName, addXYZToNameEnd);
    }

    @Override
    public UserDTO getConvertedAfterAdditionalFunctions() {
        UserDTO dto = prepareDTO();
        dto.setEmail(dto.getEmail().replace(REPLACE_LETTER_A, EMPTY_STRING));
        dto.setEmail(dto.getEmail() + LAST_LETTERS);
        return dto;
    }
}