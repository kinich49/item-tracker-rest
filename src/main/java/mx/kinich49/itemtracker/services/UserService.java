package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.models.database.User;
import mx.kinich49.itemtracker.requests.main.UserRequest;

import java.util.Optional;

public interface UserService {

    Optional<User> addUser(UserRequest userRequest) throws BusinessException;
}
