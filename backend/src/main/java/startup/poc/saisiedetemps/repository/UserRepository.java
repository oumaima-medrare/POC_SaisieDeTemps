package startup.poc.saisiedetemps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import startup.poc.saisiedetemps.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

}
