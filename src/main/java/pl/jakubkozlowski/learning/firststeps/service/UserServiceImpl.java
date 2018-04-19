package pl.jakubkozlowski.learning.firststeps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubkozlowski.learning.firststeps.converter.UserConverter;
import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;
import pl.jakubkozlowski.learning.firststeps.mapper.UserMapper;
import pl.jakubkozlowski.learning.firststeps.model.UserEntity;

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
    public UserDTO saveUser(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convert(userDTO);
        userMapper.saveUser(userEntity);
        return userConverter.convert(userEntity);
    }

    @Override
    public UserDTO findUserById(Long id) {
        return userConverter.convert(userMapper.findUserById(id));
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        return userConverter.convert(userMapper.findUserByUsername(username));
    }

    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        userMapper.updateUser(id, userConverter.convert(userDTO));
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }
}
