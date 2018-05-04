package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;

import java.util.Optional;

public interface UserService extends CRUDService<UserDTO> {

    Optional<UserDTO> findByUsername(String username);
}
