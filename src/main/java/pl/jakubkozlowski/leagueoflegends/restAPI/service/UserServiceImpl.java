package pl.jakubkozlowski.leagueoflegends.restAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubkozlowski.leagueoflegends.restAPI.converter.UserConverter;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.UserDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.mapper.UserMapper;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.UserEntity;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    private UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserConverter userConverter) {
        this.userMapper = userMapper;
        this.userConverter = userConverter;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertDTO(userDTO);
        userMapper.saveUser(userEntity);
        return userConverter.convertEntity(userEntity);
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return Optional.ofNullable(userConverter.convertEntity(userMapper.findUserById(id)));
    }

    @Override
    public void update(Long id, UserDTO userDTO) {
        userMapper.updateUser(id, userConverter.convertDTO(userDTO));
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return Optional.ofNullable(userConverter.convertEntity(userMapper.findUserByUsername(username)));
    }
}
