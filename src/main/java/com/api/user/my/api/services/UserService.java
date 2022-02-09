package com.api.user.my.api.services;

import com.api.user.my.api.domain.User;
import com.api.user.my.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO obj);
    User update(UserDTO obj);
    void delete(Integer id);
}
