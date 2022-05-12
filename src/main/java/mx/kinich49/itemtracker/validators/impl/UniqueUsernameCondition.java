package mx.kinich49.itemtracker.validators.impl;

import mx.kinich49.itemtracker.repositories.UserRepository;
import mx.kinich49.itemtracker.requests.main.UserRequest;
import mx.kinich49.itemtracker.validators.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UniqueUsernameCondition implements Condition<UserRequest> {

    private final UserRepository userRepository;

    @Autowired
    public UniqueUsernameCondition(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<String> assertCondition(UserRequest param) {
        if (userRepository.existsByUsername(param.getUsername())){
            return Optional.of(String.format("Username %s already exists", param.getUsername()));
        }

        return Optional.empty();
    }
}
