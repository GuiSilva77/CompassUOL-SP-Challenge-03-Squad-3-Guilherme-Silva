package br.com.compassuol.pb.challenge.msusers.services;

import br.com.compassuol.pb.challenge.msusers.entities.dto.UserDto;
import br.com.compassuol.pb.challenge.msusers.entities.payload.UserPayload;

public interface UserService {
    UserDto addUser(UserPayload request);

    UserDto findUserById(Long id);
    UserDto updateUser(Long id, UserPayload request);
}
