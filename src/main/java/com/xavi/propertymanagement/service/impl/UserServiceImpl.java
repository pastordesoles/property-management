package com.xavi.propertymanagement.service.impl;

import com.xavi.propertymanagement.converter.UserConverter;
import com.xavi.propertymanagement.entity.AdressEntity;
import com.xavi.propertymanagement.entity.UserEntity;
import com.xavi.propertymanagement.exception.BusinessException;
import com.xavi.propertymanagement.exception.ErrorModel;
import com.xavi.propertymanagement.model.UserDTO;
import com.xavi.propertymanagement.repository.AddressRepository;
import com.xavi.propertymanagement.repository.UserRepository;
import com.xavi.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if (optionalUserEntity.isPresent()) {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();

            errorModel.setCode("INVALID_REGISTER");
            errorModel.setMessage("User already exists");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }

        UserEntity userEntity = userConverter.convertDTOToEntity(userDTO);
        userEntity = userRepository.save(userEntity);

        AdressEntity adressEntity = new AdressEntity();
        adressEntity.setCity(userDTO.getCity());
        adressEntity.setCountry(userDTO.getCountry());
        adressEntity.setPostalCode(userDTO.getPostalCode());
        adressEntity.setStreet(userDTO.getStreet());
        adressEntity.setHouseNo(userDTO.getHouseNo());

        addressRepository.save(adressEntity);
        userDTO = userConverter.convertEntityToDTO(userEntity);

        return userDTO;
    }

    @Override
    public UserDTO login(String ownerEmail, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(ownerEmail, password);
        if (optionalUserEntity.isPresent()) {
            userDTO = userConverter.convertEntityToDTO(optionalUserEntity.get());

            return userDTO;
        } else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();

            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Invalid email or password");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
    }
}
