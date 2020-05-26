package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.edu.btl.entity.CartDetail;

import java.util.List;

public interface CartRepository extends JpaRepository<CartDetail, Integer> {
//    List<CartDetail> findByOrderId(int OrderId);
}
