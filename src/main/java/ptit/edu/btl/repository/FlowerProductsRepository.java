package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.btl.entity.FlowerProducts;

@Repository
public interface FlowerProductsRepository extends JpaRepository<FlowerProducts, Integer>, FlowerProductsRepositoryCustom {
    FlowerProducts findByNameIsLike(String name);
    void deleteById(int id);
}
