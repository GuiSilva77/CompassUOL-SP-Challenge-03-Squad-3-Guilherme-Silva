package br.com.compassuol.pb.challenge.msproducts.controllers;

import br.com.compassuol.pb.challenge.msproducts.config.TestSecurityConfig;
import br.com.compassuol.pb.challenge.msproducts.entities.Role;
import br.com.compassuol.pb.challenge.msproducts.entities.User;
import br.com.compassuol.pb.challenge.msproducts.entities.dto.RoleDto;
import br.com.compassuol.pb.challenge.msproducts.entities.dto.UserDto;
import br.com.compassuol.pb.challenge.msproducts.entities.payload.UserPayload;
import br.com.compassuol.pb.challenge.msproducts.exceptions.ResourceNotFoundException;
import br.com.compassuol.pb.challenge.msproducts.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
@Import(TestSecurityConfig.class)
class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    User user;
    UserDto userDto;

    @BeforeEach
    void setUp() {
        user = new User("Nome", "Sobrenome", "email", "password", Set.of(new Role(1L, "ADMIN"), new Role(2L, "OPERATOR")));
        userDto = new UserDto("Nome", "Sobrenome", "email",Set.of(new RoleDto(1L), new RoleDto(2L)));
    }

    @AfterEach
    void tearDown() {
        reset(userService);
    }

    @Test
    void getUserById() throws Exception {
        when(userService.findUserById(1L)).thenReturn(userDto);
        when(userService.findUserById(2L)).thenThrow(new ResourceNotFoundException("User", "id", "2"));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(userDto.firstName()))
                .andExpect(jsonPath("$.lastName").value(userDto.lastName()))
                .andExpect(jsonPath("$.email").value(userDto.email()));

        mockMvc.perform(get("/users/2"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User not found with id: '2'"));
    }

    @Test
    void createUser() throws Exception{
        when(userService.addUser(any(UserPayload.class))).thenReturn(userDto);
        UserPayload userPayload = new UserPayload("Nome", "Sobrenome", "email", "password", Set.of(new RoleDto(1L), new RoleDto(2L)));

        mockMvc.perform(post("/users").content(objectMapper.writeValueAsString(userPayload)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(userDto.firstName()))
                .andExpect(jsonPath("$.lastName").value(userDto.lastName()))
                .andExpect(jsonPath("$.email").value(userDto.email()));
    }

    @Test
    void updateUser() throws Exception{
        when(userService.updateUser(any(Long.class), any(UserPayload.class))).thenReturn(userDto);
        UserPayload userPayload = new UserPayload("Nome", "Sobrenome", "email", "password", Set.of(new RoleDto(1L), new RoleDto(2L)));

        mockMvc.perform(put("/users/1").content(objectMapper.writeValueAsString(userPayload)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(userDto.firstName()))
                .andExpect(jsonPath("$.lastName").value(userDto.lastName()))
                .andExpect(jsonPath("$.email").value(userDto.email()));
    }

}