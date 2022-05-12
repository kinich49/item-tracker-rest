package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.UserUserSecurityPropertiesDto;
import mx.kinich49.itemtracker.repositories.UserRepository;
import mx.kinich49.itemtracker.repositories.UserSecurityPropertiesRepository;
import mx.kinich49.itemtracker.services.impl.UserDetailsServiceImpl;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {

    @InjectMocks
    private UserDetailsServiceImpl subject;
    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("Sanity Test")
    public void sanityTest(){
        assertNotNull(subject);
    }

    @Test
    @DisplayName("Should throw exception when username doesnt exist")
    public void shouldThrowException_whenUsernameDoesntExist(){
        //given
        when(userRepository.findUserAndSecurityProperties(any(String.class)))
                .thenReturn(Optional.empty());

        //when
        UsernameNotFoundException result = assertThrows(UsernameNotFoundException.class, () -> subject.loadUserByUsername("Test user"));

        //then
        assertNotNull(result);

    }

    @Test
    @DisplayName("Should return DTO when username exists")
    public void shouldReturnDTO_whenUsernameExists() {
        //given
        String username = "Test user";
        UserUserSecurityPropertiesDto dto = new UserUserSecurityPropertiesDto(1L, 1L, username, "password",
                true, true, true, true);

        when(userRepository.findUserAndSecurityProperties(eq(username)))
                .thenReturn(Optional.of(dto));

        ///when
        var result = subject.loadUserByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }
}

