package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.edu.btl.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByIdAndAndFiActive(int id, boolean ac);
    List<Category> findByFiActiveOrderByName(boolean ac);
}
