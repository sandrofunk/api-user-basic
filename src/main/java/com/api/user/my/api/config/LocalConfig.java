package com.api.user.my.api.config;

import com.api.user.my.api.domain.User;
import com.api.user.my.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB() {
        User user1 = new User(null, "Name One", "userone", "userone@email.com", "123");
        User user2 = new User(null, "Name Two", "usertwo", "usertwo@email.com", "123");

        repository.saveAll(List.of(user1, user2));
    }
}
