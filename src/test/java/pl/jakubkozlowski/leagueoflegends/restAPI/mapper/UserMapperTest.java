package pl.jakubkozlowski.leagueoflegends.restAPI.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.leagueoflegends.restAPI.descriptor.UserTestConstants;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@MybatisTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    private UserEntity expectedMark;
    private UserEntity expectedKate;

    @Before
    public void SetUp() throws Exception {
        expectedMark = new UserEntity(UserTestConstants.ID_1, UserTestConstants.MARK, UserTestConstants.MARK_EMAIL, UserTestConstants.MARK_PASSWORD, UserTestConstants.MARK_FAV_ROLE_ID, UserTestConstants.MARK_FAV_CHAMP_ID);
        expectedKate = new UserEntity(UserTestConstants.ID_2, UserTestConstants.KATE, UserTestConstants.KATE_EMAIL, UserTestConstants.KATE_PASSWORD, UserTestConstants.KATE_FAV_ROLE_ID, UserTestConstants.KATE_FAV_CHAMP_ID);
    }

    @Test
    public void whenFindUserById_thenReturnUser() {
        //given
        userMapper.saveUser(expectedMark);
        //when
        UserEntity actual = userMapper.findUserById(UserTestConstants.ID_1);
        //then
        assertThat(actual)
                .isEqualTo(expectedMark);
    }

    @Test
    public void whenFindUserByName_thenReturnUncompletedUser() {
        //given
        userMapper.saveUser(expectedMark);
        //when
        UserEntity actual = userMapper.findUserByUsername(UserTestConstants.MARK);
        //then
        assertThat(actual.getUsername())
                .isEqualTo(expectedMark.getUsername());
        assertThat(actual.getPassword())
                .isNull();
    }

    @Test
    public void whenUpdateUser_thenReturnUpdatedUser() {
        //given
        userMapper.saveUser(expectedMark);
        //when
        userMapper.updateUser(UserTestConstants.ID_1, expectedKate);
        UserEntity actual = userMapper.findUserById(UserTestConstants.ID_1);
        //then
        assertThat(actual.getUsername())
                .isEqualTo(expectedKate.getUsername());
        assertThat(actual.getEmail())
                .isEqualTo(expectedKate.getEmail());
    }

    @Test
    public void whenDeleteById_thenDeleteUser() {
        //given
        userMapper.saveUser(expectedMark);
        //when
        userMapper.deleteById(UserTestConstants.ID_1);
        UserEntity actual = userMapper.findUserById(UserTestConstants.ID_1);
        //then
        assertThat(actual)
                .isNull();
    }
}