package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.btl.entity.FlowerProducts;

import java.util.List;

@Repository
public interface FlowerProductsRepository extends JpaRepository<FlowerProducts, Integer>, FlowerProductsRepositoryCustom {
    List<FlowerProducts> findByNameIsLikeOrderByName(String name);
    FlowerProducts findByNameIsLike(String name);
    void deleteById(int id);
    List<FlowerProducts> findByCategoryIdOrderByName(int id);
}
