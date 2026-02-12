package com.xavi.propertymanagement.service.impl;

import com.xavi.propertymanagement.converter.UserConverter;
import com.xavi.propertymanagement.entity.UserEntity;
import com.xavi.propertymanagement.model.UserDTO;
import com.xavi.propertymanagement.repository.UserRepository;
import com.xavi.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertDTOToEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        userDTO = userConverter.convertEntityToDTO(userEntity);

        return userDTO;
    }

    @Override
    public UserDTO login(String ownerEmail, String password) {
        return null;
    }
}
