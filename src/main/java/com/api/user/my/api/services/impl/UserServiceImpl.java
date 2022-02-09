package com.api.user.my.api.services.impl;

import com.api.user.my.api.domain.User;
import com.api.user.my.api.repositories.UserRepository;
import com.api.user.my.api.services.UserService;
import com.api.user.my.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado"));
    }
}
