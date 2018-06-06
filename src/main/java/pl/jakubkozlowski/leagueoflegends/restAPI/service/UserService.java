package pl.jakubkozlowski.leagueoflegends.restAPI.service;

import pl.jakubkozlowski.leagueoflegends.restAPI.dto.UserDTO;

import java.util.Optional;

public interface UserService extends CRUDService<UserDTO> {

    Optional<UserDTO> findByUsername(String username);
}
