package pl.jakubkozlowski.learning.firststeps.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;
import pl.jakubkozlowski.learning.firststeps.model.UserEntity;

public class UserConverterImpl implements UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO convert(UserEntity userEntity) {
        return (userEntity == null) ? null : modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    public UserEntity convert(UserDTO userDTO) {
        return (userDTO == null) ? null : modelMapper.map(userDTO, UserEntity.class);
    }
}
