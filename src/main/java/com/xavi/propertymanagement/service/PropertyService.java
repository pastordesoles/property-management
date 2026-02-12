package com.xavi.propertymanagement.service;

import com.xavi.propertymanagement.model.PropertyDTO;

import java.util.List;

public interface PropertyService {
    public PropertyDTO saveProperty(PropertyDTO propertyDTO);

    public List<PropertyDTO> getAllProperties();

    public List<PropertyDTO> getAllPropertiesForUser(Long userId);

    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId);

    public PropertyDTO patchProperty(PropertyDTO propertyDTO, Long propertyId);

    public void deleteProperty(Long propertyId);

}
