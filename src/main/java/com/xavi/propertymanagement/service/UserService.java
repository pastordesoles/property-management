package com.xavi.propertymanagement.service;

import com.xavi.propertymanagement.model.UserDTO;

public interface UserService {
    public UserDTO register(UserDTO userDTO);

    public UserDTO login(String ownerEmail, String password);
}
