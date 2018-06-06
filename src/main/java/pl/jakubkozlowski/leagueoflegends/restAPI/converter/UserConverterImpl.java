package pl.jakubkozlowski.leagueoflegends.restAPI.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.UserDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.UserEntity;

@Component
public class UserConverterImpl implements UserConverter {

    private ModelMapper modelMapper;

    @Autowired
    public UserConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

    @Override
    public Class<UserDTO> getDTOClass() {
        return UserDTO.class;
    }

    @Override
    public ModelMapper getModelMapper() {
        return modelMapper;
    }

}