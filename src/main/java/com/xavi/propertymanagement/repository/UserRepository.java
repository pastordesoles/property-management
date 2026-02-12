package com.xavi.propertymanagement.repository;

import com.xavi.propertymanagement.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByOwnerEmailAndPassword(String ownerEmail, String password);

    Optional<UserEntity> findByOwnerEmail(String ownerEmail);
}
