package com.xavi.propertymanagement.repository;

import com.xavi.propertymanagement.entity.AdressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AdressEntity, Long> {
}
