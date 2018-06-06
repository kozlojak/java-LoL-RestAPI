package pl.jakubkozlowski.leagueoflegends.restAPI.converter;

import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.UserDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.UserEntity;

import java.util.Arrays;
import java.util.List;

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
        return new UserDTO();
    }

    @Override
    public UserEntity prepareEntity() {
        return new UserEntity();
    }

    @Override
    public List<UserDTO> prepareListDTO() {
        return Arrays.asList(new UserDTO(), new UserDTO());
    }

    @Override
    public List<UserEntity> prepareListEntity() {
        return Arrays.asList(new UserEntity(), new UserEntity());
    }

    @Override
    public ModelMapperConverter getConverter() {
        return userConverter;
    }
}