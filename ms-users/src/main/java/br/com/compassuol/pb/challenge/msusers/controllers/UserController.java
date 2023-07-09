package br.com.compassuol.pb.challenge.msusers.controllers;

import br.com.compassuol.pb.challenge.msusers.entities.dto.UserDto;
import br.com.compassuol.pb.challenge.msusers.entities.payload.UserPayload;
import br.com.compassuol.pb.challenge.msusers.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        UserDto user = userService.findUserById(id);

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserPayload request) {
        UserDto user = userService.addUser(request);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long id, @Valid @RequestBody UserPayload request) {
        UserDto user = userService.updateUser(id, request);

        return ResponseEntity.ok(user);
    }
}
