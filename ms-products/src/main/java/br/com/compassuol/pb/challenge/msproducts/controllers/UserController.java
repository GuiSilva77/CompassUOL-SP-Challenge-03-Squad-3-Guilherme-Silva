package br.com.compassuol.pb.challenge.msproducts.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("{id}")
    public String getUserById() {
        return "User";
    }

    @PostMapping
    public String createUser() {
        return "User";
    }

    @PutMapping("{id}")
    public String updateUser() {
        return "User";
    }
}
