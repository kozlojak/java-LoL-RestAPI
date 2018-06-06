package pl.jakubkozlowski.leagueoflegends.restAPI.converter;

import pl.jakubkozlowski.leagueoflegends.restAPI.dto.UserDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.UserEntity;

public interface UserConverter extends ModelMapperConverter<UserDTO, UserEntity> {
}
