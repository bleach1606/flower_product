package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.edu.btl.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
