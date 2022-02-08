package com.api.user.my.api.services;

import com.api.user.my.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
