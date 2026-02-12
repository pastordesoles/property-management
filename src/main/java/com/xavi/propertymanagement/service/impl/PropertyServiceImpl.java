package com.xavi.propertymanagement.service.impl;

import com.xavi.propertymanagement.converter.PropertyConverter;
import com.xavi.propertymanagement.entity.PropertyEntity;
import com.xavi.propertymanagement.entity.UserEntity;
import com.xavi.propertymanagement.exception.BusinessException;
import com.xavi.propertymanagement.exception.ErrorModel;
import com.xavi.propertymanagement.model.PropertyDTO;
import com.xavi.propertymanagement.repository.PropertyRepository;
import com.xavi.propertymanagement.repository.UserRepository;
import com.xavi.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(propertyDTO.getUserId());
        if (optionalUserEntity.isPresent()) {
            PropertyEntity propertyEntity = propertyConverter.convertDTOToEntity(propertyDTO);
            propertyEntity.setUserEntity(optionalUserEntity.get());

            propertyEntity = propertyRepository.save(propertyEntity);
            propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);
        } else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();

            errorModel.setCode("USER_DOES_NOT_EXIST");
            errorModel.setMessage("User doesn't exist");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }


        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> propertyList = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propertyDTOList = new ArrayList<>();
        for (PropertyEntity propertyEntity : propertyList) {
            PropertyDTO propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);
            propertyDTOList.add(propertyDTO);
        }

        return propertyDTOList;
    }

    @Override
    public List<PropertyDTO> getAllPropertiesForUser(Long userId) {
        List<PropertyEntity> propertyList = (List<PropertyEntity>) propertyRepository.findAllByUserEntityId(userId);
        List<PropertyDTO> propertyDTOList = new ArrayList<>();
        for (PropertyEntity propertyEntity : propertyList) {
            PropertyDTO propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);
            propertyDTOList.add(propertyDTO);
        }

        return propertyDTOList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO propertyDTOResult = null;

        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalPropertyEntity.get();
            propertyConverter.updateEntityFromDTO(propertyEntity, propertyDTO);
            propertyRepository.save(propertyEntity);

            propertyDTOResult = propertyConverter.convertEntityToDTO(propertyEntity);
        }

        return propertyDTOResult;
    }

    @Override
    public PropertyDTO patchProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO propertyDTOResult = null;
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalPropertyEntity.get();
            propertyConverter.patchEntityFromDTO(propertyEntity, propertyDTO);
            propertyRepository.save(propertyEntity);

            propertyDTOResult = propertyConverter.convertEntityToDTO(propertyEntity);
        }
        return propertyDTOResult;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }


}
