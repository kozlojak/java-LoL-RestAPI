package pl.jakubkozlowski.learning.firststeps.service;

import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;

public interface UserService {

    void saveUser(UserDTO userDTO);

    UserDTO findUserById(Long id);

    UserDTO findUserByName(String name);

    void updateUser(Long id, UserDTO userDTO);

    void deleteById(Long id);
}
