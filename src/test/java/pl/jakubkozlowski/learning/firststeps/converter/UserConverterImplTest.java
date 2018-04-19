package pl.jakubkozlowski.learning.firststeps.converter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;
import pl.jakubkozlowski.learning.firststeps.model.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jakubkozlowski.learning.firststeps.descriptor.UserTestConstants.*;

@RunWith(SpringRunner.class)
public class UserConverterImplTest {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserConverter userConverter;

    @TestConfiguration
    static class UserConverterImplTestContextConfiguration {

        @Bean
        public UserConverter userConverter() {
            return new UserConverterImpl();
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    }

    private UserEntity userEntityMark;
    private UserDTO userDTOMark;

    @Before
    public void setUp() throws Exception {
        userEntityMark = new UserEntity(ID_1, MARK, MARK_EMAIL, MARK_PASSWORD, MARK_FAV_ROLE_ID, MARK_FAV_CHAMP_ID);
        userDTOMark = new UserDTO(ID_1, MARK, MARK_EMAIL, MARK_PASSWORD, MARK_FAV_ROLE_ID, MARK_FAV_CHAMP_ID);
    }

    @Test
    public void whenConvertUserEntity_thenReturnUserDTO() {
        //when
        UserDTO actual = userConverter.convert(userEntityMark);
        //then
        assertThat(actual)
                .isEqualTo(userDTOMark);
    }

    @Test
    public void whenConvertChampionDTO_thenReturnChampionEntity() {
        //when
        UserEntity actual = userConverter.convert(userDTOMark);
        //then
        assertThat(actual)
                .isEqualTo(userEntityMark);
    }
}