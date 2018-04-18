package pl.jakubkozlowski.learning.firststeps.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubkozlowski.learning.firststeps.converter.UserConverter;
import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;
import pl.jakubkozlowski.learning.firststeps.mapper.UserMapper;

public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    private UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserConverter userConverter) {
        this.userMapper = userMapper;
        this.userConverter = userConverter;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        userMapper.saveUser(userConverter.convert(userDTO));
    }

    @Override
    public UserDTO findUserById(Long id) {
        return userConverter.convert(userMapper.findUserById(id));
    }

    @Override
    public UserDTO findUserByName(String name) {
        return userConverter.convert(userMapper.findUserByName(name));
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
