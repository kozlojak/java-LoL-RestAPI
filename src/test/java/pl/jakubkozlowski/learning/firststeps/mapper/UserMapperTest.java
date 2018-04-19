package pl.jakubkozlowski.learning.firststeps.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.learning.firststeps.model.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jakubkozlowski.learning.firststeps.descriptor.UserTestConstants.*;

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
        expectedMark = new UserEntity(ID_1, MARK, MARK_EMAIL, MARK_PASSWORD, MARK_FAV_ROLE_ID, MARK_FAV_CHAMP_ID);
        expectedKate = new UserEntity(ID_2, KATE, KATE_EMAIL, KATE_PASSWORD, KATE_FAV_ROLE_ID, KATE_FAV_CHAMP_ID);
    }

    @Test
    public void whenFindUserById_thenReturnUser() {
        //given
        userMapper.saveUser(expectedMark);
        //when
        UserEntity actual = userMapper.findUserById(ID_1);
        //then
        assertThat(actual)
                .isEqualTo(expectedMark);
    }

    @Test
    public void whenFindUserByName_thenReturnUncompletedUser() {
        //given
        userMapper.saveUser(expectedMark);
        //when
        UserEntity actual = userMapper.findUserByUsername(MARK);
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
        userMapper.updateUser(ID_1, expectedKate);
        UserEntity actual = userMapper.findUserById(ID_1);
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
        userMapper.deleteById(ID_1);
        UserEntity actual = userMapper.findUserById(ID_1);
        //then
        assertThat(actual)
                .isNull();
    }
}