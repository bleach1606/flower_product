package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.btl.entity.FlowerProducts;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlowerProductsRepository extends JpaRepository<FlowerProducts, Integer>, FlowerProductsRepositoryCustom {
    List<FlowerProducts> findByNameIsLikeOrderByName(String name);
    FlowerProducts findByNameIsLike(String name);
    void deleteById(int id);
    List<FlowerProducts> findByCategoryIdAndActiveOrderByName(int id, boolean bo);
    Optional<FlowerProducts> findByIdAndActive(int id, boolean bo);
}
