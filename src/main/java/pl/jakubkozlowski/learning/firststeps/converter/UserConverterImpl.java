package pl.jakubkozlowski.learning.firststeps.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;
import pl.jakubkozlowski.learning.firststeps.model.UserEntity;

@Component
public class UserConverterImpl implements UserConverter {

    private ModelMapper modelMapper;

    @Autowired
    public UserConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO convert(UserEntity userEntity) {
        return (userEntity == null) ? null : modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    public UserEntity convert(UserDTO userDTO) {
        return (userDTO == null) ? null : modelMapper.map(userDTO, UserEntity.class);
    }
}
