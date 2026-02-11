package com.xavi.propertymanagement.service.impl;

import com.xavi.propertymanagement.converter.PropertyConverter;
import com.xavi.propertymanagement.entity.PropertyEntity;
import com.xavi.propertymanagement.model.PropertyDTO;
import com.xavi.propertymanagement.repository.PropertyRepository;
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

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        PropertyEntity propertyEntity = propertyConverter.convertDTOToEntity(propertyDTO);

        propertyEntity = propertyRepository.save(propertyEntity);
        propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);

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
