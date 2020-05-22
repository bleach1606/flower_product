package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.btl.entity.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsernameAndPassword(String username, String password);
    Optional<Users> findByUsername(String username);
    Users findById(int id);
}
