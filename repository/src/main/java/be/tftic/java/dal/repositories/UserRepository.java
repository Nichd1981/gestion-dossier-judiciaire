package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u where u.mail like :mail")
    Optional<User> getUserByUsername(String mail);
}
