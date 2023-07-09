package br.com.compassuol.pb.challenge.msusers.services.impl;

import br.com.compassuol.pb.challenge.msusers.entities.Role;
import br.com.compassuol.pb.challenge.msusers.entities.User;
import br.com.compassuol.pb.challenge.msusers.entities.dto.RoleDto;
import br.com.compassuol.pb.challenge.msusers.entities.dto.UserDto;
import br.com.compassuol.pb.challenge.msusers.entities.payload.UserPayload;
import br.com.compassuol.pb.challenge.msusers.exceptions.ResourceNotFoundException;
import br.com.compassuol.pb.challenge.msusers.repositories.UserRepository;
import br.com.compassuol.pb.challenge.msusers.services.UserNotificationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserNotificationService userNotificationService;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    User user;

    UserDto userDto;

    @BeforeEach
    void setUp() {
        user = new User("Nome", "Sobrenome", "email", "password", Set.of(new Role(1L, "ADMIN"), new Role(2L, "OPERATOR")));
        userDto = new UserDto("Nome", "Sobrenome", "email",Set.of(new RoleDto(1L), new RoleDto(2L)));
    }

    @AfterEach
    void tearDown() {
        reset(userRepository);
    }

    @Test
    void addUser() {
        UserPayload userPayload = new UserPayload("Nome", "Sobrenome", "email", "password", Set.of(new RoleDto(1L), new RoleDto(2L)));

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(any(String.class))).thenReturn("password");

        UserDto userDto = userServiceImpl.addUser(userPayload);

        assertEquals(userDto, this.userDto);
    }

    @Test
    void findUserById() {
        when(userRepository.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(user));

        UserDto userDto = userServiceImpl.findUserById(1L);

        assertEquals(userDto, this.userDto);
    }

    @Test
    void findUserByIdNotFound() {
        when(userRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.findUserById(1L));
    }

    @Test
    void updateUser() {
        UserPayload userPayload = new UserPayload("Nome", "Sobrenome", "email", "password", Set.of(new RoleDto(1L), new RoleDto(2L)));

        when(userRepository.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(any(String.class))).thenReturn("password");

        UserDto userDto = userServiceImpl.updateUser(1L, userPayload);

        assertEquals(userDto, this.userDto);
    }

    @Test
    void updateUserNotFound() {
        UserPayload userPayload = new UserPayload("Nome", "Sobrenome", "email", "password", Set.of(new RoleDto(1L), new RoleDto(2L)));

        when(userRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.updateUser(1L, userPayload));
    }
}