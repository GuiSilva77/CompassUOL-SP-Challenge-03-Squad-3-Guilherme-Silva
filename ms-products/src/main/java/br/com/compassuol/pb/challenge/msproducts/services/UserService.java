package br.com.compassuol.pb.challenge.msproducts.services;

import br.com.compassuol.pb.challenge.msproducts.entities.dto.UserDto;
import br.com.compassuol.pb.challenge.msproducts.entities.payload.UserPayload;

public interface UserService {
    UserDto addUser(UserPayload request);

    UserDto findUserById(Long id);
    UserDto updateUser(Long id, UserPayload request);
}
