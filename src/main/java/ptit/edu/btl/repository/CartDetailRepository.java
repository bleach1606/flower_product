package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.btl.entity.CartDetail;

import java.util.List;
@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
//    List<CartDetail> findByOrderBillId(int OrderId);
}
