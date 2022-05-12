package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.database.UserSecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSecurityPropertiesRepository extends JpaRepository<UserSecurityProperties, Long> {
}
