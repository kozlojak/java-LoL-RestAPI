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
import pl.jakubkozlowski.learning.firststeps.converter.UserConverter;
import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;
import pl.jakubkozlowski.learning.firststeps.mapper.UserMapper;
import pl.jakubkozlowski.learning.firststeps.model.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jakubkozlowski.learning.firststeps.descriptor.UserTestConstants.*;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserConverter userConverter;

    @Before
    public void setUp() throws Exception {
        userEntityMark = new UserEntity(ID_1, MARK, MARK_EMAIL, MARK_PASSWORD, MARK_FAV_ROLE_ID, MARK_FAV_CHAMP_ID);
        userDTOMark = new UserDTO(ID_1, MARK, MARK_EMAIL, MARK_PASSWORD, MARK_FAV_ROLE_ID, MARK_FAV_CHAMP_ID);
        userEntityMarkWithSelectedFields = new UserEntity(MARK, MARK_EMAIL, MARK_FAV_ROLE_ID, MARK_FAV_CHAMP_ID);
        userDTOMarkWithSelectedFields = new UserDTO(MARK, MARK_EMAIL, MARK_FAV_ROLE_ID, MARK_FAV_CHAMP_ID);

        Mockito.when(userMapper.findUserById(userEntityMark.getId()))
                .thenReturn(userEntityMark);
        Mockito.when(userMapper.findUserByUsername(userEntityMark.getUsername()))
                .thenReturn(userEntityMarkWithSelectedFields);
        Mockito.when(userConverter.convertDTO(userDTOMark))
                .thenReturn(userEntityMark);
        Mockito.when(userConverter.convertEntity(userEntityMark))
                .thenReturn(userDTOMark);
        Mockito.when(userConverter.convertDTO(userDTOMarkWithSelectedFields))
                .thenReturn(userEntityMarkWithSelectedFields);
        Mockito.when(userConverter.convertEntity(userEntityMarkWithSelectedFields))
                .thenReturn(userDTOMarkWithSelectedFields);
    }

    private UserEntity userEntityMark;
    private UserDTO userDTOMark;
    private UserEntity userEntityMarkWithSelectedFields;
    private UserDTO userDTOMarkWithSelectedFields;

    @Test
    public void whenFindById_thenReturnUserDTO() {
        //when
        UserDTO actual = userService.findById(userEntityMark.getId());
        //then
        assertThat(actual)
                .isEqualTo(userDTOMark);
    }

    @Test
    public void whenFindByName_thenReturnUncompletedUserDTO() {
        //when
        UserDTO actual = userService.findByUsername(userEntityMark.getUsername());
        //then
        assertThat(actual)
                .isEqualTo(userDTOMarkWithSelectedFields);
    }

    @Test
    public void whenSave_thenReturnUserDTO() {
        //when
        UserDTO actual = userService.save(userDTOMark);
        //then
        assertThat(actual)
                .isEqualTo(userDTOMark);
    }

    @Test
    public void whenUpdate_thenReturnUpdatedUserDTO() {
        //when
        userService.update(ID_1, userDTOMark);
        //then
        Mockito.verify(userMapper, Mockito.times(1)).updateUser(ID_1, userEntityMark);
    }

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @MockBean
        private UserMapper userMapper;

        @MockBean
        private UserConverter userConverter;

        @Bean
        public UserService userService() {
            return new UserServiceImpl(userMapper, userConverter);
        }
    }

    @Test
    public void whenDeleteById_thenDeleteUserDTO() {
        //when
        userService.deleteById(ID_1);
        //then
        Mockito.verify(userMapper, Mockito.times(1)).deleteById(ID_1);
    }

}