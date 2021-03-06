package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
