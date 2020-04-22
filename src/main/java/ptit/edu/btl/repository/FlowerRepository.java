package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ptit.edu.btl.entity.Flower;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Integer> {

    List<Flower> findAllByFromCountry(String country);

    Optional<Flower> findByName(String flowerName);

    @Query(nativeQuery = true, value = "SELECT * FROM flower f " +
            "JOIN token t on f.id = t.id " +
            "where f.name like CONCAT('%',?1,'%') and t.token = ?2")
    List<Flower> findFlowerByName(String name, String token);
//    JPA Criteria

}
