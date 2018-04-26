package pl.jakubkozlowski.learning.firststeps.converter;

import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;
import pl.jakubkozlowski.learning.firststeps.model.UserEntity;

public interface UserConverter {

    UserDTO convert(UserEntity userEntity);

    UserEntity convert(UserDTO userDTO);
}
