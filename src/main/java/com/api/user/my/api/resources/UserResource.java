package com.api.user.my.api.resources;

import com.api.user.my.api.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @GetMapping(value = "/id")
    public ResponseEntity<User> findById(@PathVariable Integer id) {

        return ResponseEntity.ok().body(new User(1, "João da Silva", "joaosilva",
                "joao.silva@email.com", "1234"));
    }


}
