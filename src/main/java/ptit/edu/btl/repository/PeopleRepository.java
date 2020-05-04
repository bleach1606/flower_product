package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.btl.entity.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {
    void deleteById(int id);
    People findById(int id);
}
