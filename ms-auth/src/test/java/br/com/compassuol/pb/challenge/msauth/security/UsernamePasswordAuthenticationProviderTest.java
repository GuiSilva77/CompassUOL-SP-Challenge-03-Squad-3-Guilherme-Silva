package br.com.compassuol.pb.challenge.msauth.security;

import br.com.compassuol.pb.challenge.msauth.entities.Role;
import br.com.compassuol.pb.challenge.msauth.entities.User;
import br.com.compassuol.pb.challenge.msauth.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsernamePasswordAuthenticationProviderTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    Authentication validAuthentication;
    Authentication invalidAuthentication;

    @BeforeEach
    void setUp() {
        validAuthentication = new UsernamePasswordAuthenticationToken("person@email.com", "password");
        invalidAuthentication = new UsernamePasswordAuthenticationToken("invalid", "invalid");
    }

    @AfterEach
    void tearDown() {
        reset(userRepository);
    }

    @Test
    void authenticate() {
        User user = new User(1L, "Person", "person", "person@email.com", "$2a$10$DSwS0I2DN26FzxNLA3oNROLvIHUgH1Rjb9XJcLKF9BBbq27mIAhvO", Set.of(new Role(1L, "ADMIN"), new Role(2L, "OPERATOR")));
        when(userRepository.findByEmail(any(String.class))).thenReturn(user);
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(true);

        Authentication authentication = usernamePasswordAuthenticationProvider.authenticate(validAuthentication);

        assertNotNull(authentication);
    }

    @Test
    void authenticateNotAuthenticated() {
        User user = new User(1L, "Person", "person", "person@email.com", "$2a$10$DSwS0I2DN26FzxNLA3oNROLvIHUgH1Rjb9XJcLKF9BBbq27mIAhvO", Set.of(new Role(1L, "ADMIN"), new Role(2L, "OPERATOR")));
        when(userRepository.findByEmail(any(String.class))).thenReturn(user);
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(false);

        assertThrows(BadCredentialsException.class, () -> usernamePasswordAuthenticationProvider.authenticate(validAuthentication));
    }

    @Test
    void authenticateNotFound() {
        when(userRepository.findByEmail(any(String.class))).thenReturn(null);

        assertThrows(BadCredentialsException.class, () -> usernamePasswordAuthenticationProvider.authenticate(validAuthentication));
    }
}