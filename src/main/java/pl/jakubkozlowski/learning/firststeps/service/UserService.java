package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);

    UserDTO findUserById(Long id);

    UserDTO findUserByUsername(String username);

    void updateUser(Long id, UserDTO userDTO);

    void deleteById(Long id);
}
