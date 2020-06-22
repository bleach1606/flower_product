package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.btl.entity.Category;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByIdAndAndActive(int id, boolean ac);
    List<Category> findByActiveOrderByName(boolean ac);
}
