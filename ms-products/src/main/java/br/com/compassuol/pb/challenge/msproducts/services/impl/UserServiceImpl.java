package br.com.compassuol.pb.challenge.msproducts.services.impl;

import br.com.compassuol.pb.challenge.msproducts.entities.Role;
import br.com.compassuol.pb.challenge.msproducts.entities.User;
import br.com.compassuol.pb.challenge.msproducts.entities.dto.UserDto;
import br.com.compassuol.pb.challenge.msproducts.entities.payload.UserPayload;
import br.com.compassuol.pb.challenge.msproducts.exceptions.ResourceNotFoundException;
import br.com.compassuol.pb.challenge.msproducts.repositories.UserRepository;
import br.com.compassuol.pb.challenge.msproducts.services.UserNotificationService;
import br.com.compassuol.pb.challenge.msproducts.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserNotificationService userNotificationService;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserNotificationService userNotificationService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userNotificationService = userNotificationService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDto addUser(UserPayload request) {
        User user = request.toUser();

        user.setPassword(passwordEncoder.encode(request.password()));

        User savedUser = userRepository.save(user);

        userNotificationService.sendNewUserNotification(savedUser.getEmail(), savedUser.getFirstName());

        return savedUser.toUserDto();
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id.toString())
        );

        return user.toUserDto();
    }

    @Override
    public UserDto updateUser(Long id, UserPayload request) {
        User updateUser = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id.toString())
        );

        updateUser.setFirstName(request.firstName());
        updateUser.setLastName(request.lastName());
        updateUser.setEmail(request.email());
        updateUser.setPassword(passwordEncoder.encode(request.password()));
        updateUser.setRoles(request.roles().stream().map(role -> new Role(role.id())).collect(Collectors.toSet()));

        User newUser =  userRepository.save(updateUser);

        userNotificationService.sendUserModifiedNotification(newUser.getEmail(), newUser.getFirstName());

        return newUser.toUserDto();
    }
}
