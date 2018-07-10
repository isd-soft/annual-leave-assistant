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
<<<<<<< HEAD
    List<User> findUserBySurname(String surname);
=======
    List<User> findAllByRole(Role role);
>>>>>>> 1b022eb2ec59bd63b533b8805d7c774f57dbd6ca
}
