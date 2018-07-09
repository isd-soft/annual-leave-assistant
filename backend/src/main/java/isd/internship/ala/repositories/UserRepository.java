package isd.internship.ala.repositories;

import isd.internship.ala.models.Role;
import isd.internship.ala.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    List<User> findAllByRole(Role role);
}
