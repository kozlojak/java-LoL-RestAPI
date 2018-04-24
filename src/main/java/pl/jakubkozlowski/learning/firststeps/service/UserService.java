package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;

public interface UserService extends CRUDservice<UserDTO> {

    UserDTO findByUsername(String username);
}
