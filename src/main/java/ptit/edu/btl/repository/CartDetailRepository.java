package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.edu.btl.entity.CartDetail;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
//    List<CartDetail> findByOrderBillId(int OrderId);
}
