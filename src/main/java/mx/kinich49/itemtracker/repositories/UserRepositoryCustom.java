package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.dtos.UserUserSecurityPropertiesDto;
import mx.kinich49.itemtracker.models.database.User;

import java.util.Optional;

public interface UserRepositoryCustom {

    User encryptPasswordAndSave(User user);

    Optional<UserUserSecurityPropertiesDto> findUserAndSecurityProperties(String username);
}
