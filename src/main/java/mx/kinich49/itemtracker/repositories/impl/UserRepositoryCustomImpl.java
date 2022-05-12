package mx.kinich49.itemtracker.repositories.impl;

import mx.kinich49.itemtracker.dtos.UserUserSecurityPropertiesDto;
import mx.kinich49.itemtracker.models.database.User;
import mx.kinich49.itemtracker.repositories.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager manager;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryCustomImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User encryptPasswordAndSave(User user) {
        if (user == null)
            return null;

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        manager.persist(user);
        manager.refresh(user);
        return user;
    }

    @Override
    public Optional<UserUserSecurityPropertiesDto> findUserAndSecurityProperties(String username) {

//        private final Long userId;
//        private final Long userSecurityPropertiesId;
//        private final String username;
//        private final String password;
//        private final boolean isAccountNonExpired;
//        private final boolean isAccountNonLocked;
//        private final boolean isCredentialsNonExpired;
//        private final boolean isEnabled;
        String dtoQuery = "SELECT new mx.kinich49.itemtracker.dtos.UserUserSecurityPropertiesDto(" +
                "u.id, usp.id, u.username, u.password, usp.isAccountNonExpired, usp.isAccountNonLocked, " +
                "usp.isAccountNonExpired, usp.isEnabled) " +
                "FROM User u " +
                "JOIN UserSecurityProperties usp " +
                "ON usp.user.id = u.id " +
                "WHERE u.username = :username ";
        String query = "SELECT u.id, usp.id, u.username, u.password, " +
                "usp.id, usp.isAccountNonExpired, usp.isAccountNonLocked, usp.isCredentialsNonExpired, usp.isEnabled " +
                "FROM User u " +
                "JOIN UserSecurityProperties usp " +
                "ON usp.user.id = u.id " +
                "WHERE u.username = :username ";

        UserUserSecurityPropertiesDto dto = (UserUserSecurityPropertiesDto) manager.createQuery(dtoQuery)
                .setParameter("username", username)
                .getSingleResult();

        return Optional.of(dto);
    }
}
