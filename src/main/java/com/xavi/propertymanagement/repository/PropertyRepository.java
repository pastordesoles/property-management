package com.xavi.propertymanagement.repository;

import com.xavi.propertymanagement.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {
    List<PropertyEntity> findAllByUserEntityId(Long userId);
}
