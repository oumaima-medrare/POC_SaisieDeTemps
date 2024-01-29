package startup.poc.saisiedetemps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import startup.poc.saisiedetemps.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    public List<User> findAllByManagerId(Long userId);


}
