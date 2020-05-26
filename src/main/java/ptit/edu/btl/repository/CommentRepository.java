package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.btl.entity.Comment;
import ptit.edu.btl.entity.People;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByFlowerProductsId(int id);
}
