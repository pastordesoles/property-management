package com.xavi.propertymanagement.repository;

import com.xavi.propertymanagement.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {
}
