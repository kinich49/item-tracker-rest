package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.models.database.User;
import mx.kinich49.itemtracker.models.database.UserSecurityProperties;
import mx.kinich49.itemtracker.repositories.UserRepository;
import mx.kinich49.itemtracker.repositories.UserSecurityPropertiesRepository;
import mx.kinich49.itemtracker.requests.main.UserRequest;
import mx.kinich49.itemtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserSecurityPropertiesRepository userSecurityPropertiesRepository;
    private final UserRequestValidator validator;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserSecurityPropertiesRepository userSecurityPropertiesRepository,
                           UserRequestValidator validator,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userSecurityPropertiesRepository = userSecurityPropertiesRepository;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Optional<User> addUser(UserRequest userRequest) throws BusinessException {
        validator.validate(userRequest);

        User user = new User();
        UserSecurityProperties securityProperties = new UserSecurityProperties();
        setDefaultValues(securityProperties);
        setValues(userRequest, user, securityProperties);

        User persistedUser = userRepository.save(user);
        userSecurityPropertiesRepository.save(securityProperties);
        return Optional.of(persistedUser);
    }

    private void setDefaultValues(UserSecurityProperties securityProperties) {
        securityProperties.setAccountNonLocked(true);
        securityProperties.setEnabled(true);
        securityProperties.setCredentialsNonExpired(true);
        securityProperties.setAccountNonExpired(true);
    }

    private void setValues(UserRequest userRequest,
                           User user,
                           UserSecurityProperties userSecurityProperties) {
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setUsername(userRequest.getUsername());
        userSecurityProperties.setUser(user);
    }
}
