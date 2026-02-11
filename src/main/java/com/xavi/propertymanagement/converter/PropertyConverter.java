package com.xavi.propertymanagement.converter;

import com.xavi.propertymanagement.entity.PropertyEntity;
import com.xavi.propertymanagement.model.PropertyDTO;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {

    public PropertyEntity convertDTOToEntity(PropertyDTO propertyDTO) {
        PropertyEntity propertyEntity = new PropertyEntity();

        propertyEntity.setAddress(propertyDTO.getAddress());
        propertyEntity.setDescription(propertyDTO.getDescription());
        propertyEntity.setTitle(propertyDTO.getTitle());
        propertyEntity.setPrice(propertyDTO.getPrice());
        propertyEntity.setOwnerEmail(propertyDTO.getOwnerEmail());
        propertyEntity.setOwnerName(propertyDTO.getOwnerName());

        return propertyEntity;
    }

    public PropertyDTO convertEntityToDTO(PropertyEntity propertyEntity) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(propertyEntity.getId());
        propertyDTO.setTitle(propertyEntity.getTitle());
        propertyDTO.setAddress(propertyEntity.getAddress());
        propertyDTO.setPrice(propertyEntity.getPrice());
        propertyDTO.setDescription(propertyEntity.getDescription());
        propertyDTO.setOwnerEmail(propertyEntity.getOwnerEmail());
        propertyDTO.setOwnerName(propertyEntity.getOwnerName());


        return propertyDTO;

    }

    public PropertyEntity updateEntityFromDTO(PropertyEntity propertyEntity, PropertyDTO propertyDTO) {
        propertyEntity.setAddress(propertyDTO.getAddress());
        propertyEntity.setDescription(propertyDTO.getDescription());
        propertyEntity.setTitle(propertyDTO.getTitle());
        propertyEntity.setPrice(propertyDTO.getPrice());
        propertyEntity.setOwnerEmail(propertyDTO.getOwnerEmail());
        propertyEntity.setOwnerName(propertyDTO.getOwnerName());

        return propertyEntity;
    }

    public PropertyEntity patchEntityFromDTO(PropertyEntity propertyEntity, PropertyDTO propertyDTO) {
        if (propertyDTO.getAddress() != null) {
            propertyEntity.setAddress(propertyDTO.getAddress());
        }
        if (propertyDTO.getDescription() != null) {
            propertyEntity.setDescription(propertyDTO.getDescription());
        }
        if (propertyDTO.getTitle() != null) {
            propertyEntity.setTitle(propertyDTO.getTitle());
        }
        if (propertyDTO.getPrice() != null) {
            propertyEntity.setPrice(propertyDTO.getPrice());
        }
        if (propertyDTO.getOwnerEmail() != null) {
            propertyEntity.setOwnerEmail(propertyDTO.getOwnerEmail());
        }
        if (propertyDTO.getOwnerName() != null) {
            propertyEntity.setOwnerName(propertyDTO.getOwnerName());
        }

        return propertyEntity;
    }
}
