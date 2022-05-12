package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.UserUserSecurityPropertiesDto;
import mx.kinich49.itemtracker.models.database.User;
import mx.kinich49.itemtracker.repositories.UserRepository;
import mx.kinich49.itemtracker.repositories.UserSecurityPropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserSecurityPropertiesRepository userSecurityPropertiesRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository,
                                  UserSecurityPropertiesRepository userSecurityPropertiesRepository) {
        this.userRepository = userRepository;
        this.userSecurityPropertiesRepository = userSecurityPropertiesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserAndSecurityProperties(username)
                .map(mapToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("username %s not found", username)));
    }

    private final Function<UserUserSecurityPropertiesDto, UserDetails> mapToUserDetails = dto -> new UserDetails() {

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.emptyList();
        }

        @Override
        public String getPassword() {
            return dto.getPassword();
        }

        @Override
        public String getUsername() {
            return dto.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return dto.isAccountNonExpired();
        }

        @Override
        public boolean isAccountNonLocked() {
            return dto.isAccountNonLocked();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return dto.isCredentialsNonExpired();
        }

        @Override
        public boolean isEnabled() {
            return dto.isEnabled();
        }
    };

}
