package pl.jakubkozlowski.leagueoflegends.restAPI.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.leagueoflegends.restAPI.converter.UserConverter;
import pl.jakubkozlowski.leagueoflegends.restAPI.descriptor.UserTestConstants;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.UserDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.mapper.UserMapper;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserConverter userConverter;

    @Test
    public void whenFindById_thenReturnUserDTO() {
        //when
        UserDTO actual = userService.findById(userEntityMark.getId()).get();
        //then
        assertThat(actual)
                .isEqualTo(userDTOMark);
    }

    private UserEntity userEntityMark;
    private UserDTO userDTOMark;
    private UserEntity userEntityMarkWithSelectedFields;
    private UserDTO userDTOMarkWithSelectedFields;

    @Test
    public void whenFindByName_thenReturnUncompletedUserDTO() {
        //when
        UserDTO actual = userService.findByUsername(userEntityMark.getUsername()).get();
        //then
        assertThat(actual)
                .isEqualTo(userDTOMarkWithSelectedFields);
    }

    @Before
    public void setUp() throws Exception {
        userEntityMark = new UserEntity(UserTestConstants.ID_1, UserTestConstants.MARK, UserTestConstants.MARK_EMAIL, UserTestConstants.MARK_PASSWORD, UserTestConstants.MARK_FAV_ROLE_ID, UserTestConstants.MARK_FAV_CHAMP_ID);
        userDTOMark = new UserDTO(UserTestConstants.ID_1, UserTestConstants.MARK, UserTestConstants.MARK_EMAIL, UserTestConstants.MARK_PASSWORD, UserTestConstants.MARK_FAV_ROLE_ID, UserTestConstants.MARK_FAV_CHAMP_ID);
        userEntityMarkWithSelectedFields = new UserEntity(UserTestConstants.MARK, UserTestConstants.MARK_EMAIL, UserTestConstants.MARK_FAV_ROLE_ID, UserTestConstants.MARK_FAV_CHAMP_ID);
        userDTOMarkWithSelectedFields = new UserDTO(UserTestConstants.MARK, UserTestConstants.MARK_EMAIL, UserTestConstants.MARK_FAV_ROLE_ID, UserTestConstants.MARK_FAV_CHAMP_ID);

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
        userService.update(UserTestConstants.ID_1, userDTOMark);
        //then
        Mockito.verify(userMapper, Mockito.times(1)).updateUser(UserTestConstants.ID_1, userEntityMark);
    }

    @Test
    public void whenDeleteById_thenDeleteUserDTO() {
        //when
        userService.deleteById(UserTestConstants.ID_1);
        //then
        Mockito.verify(userMapper, Mockito.times(1)).deleteById(UserTestConstants.ID_1);
    }

}