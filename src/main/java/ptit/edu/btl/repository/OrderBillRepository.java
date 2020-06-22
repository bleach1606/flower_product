package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.btl.entity.OrderBill;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderBillRepository extends JpaRepository<OrderBill, Integer> {
    List<OrderBill> findByUsers_idAndActive(int id, boolean active);
    List<OrderBill> findByUsersAndActive(int id, boolean active);
    List<OrderBill> findByUsers_idAndStatusAndActive(int id, int status, boolean active);
    Optional<OrderBill> findFirstByUsers_idAndStatusAndActive(int id, int status, boolean active);
    List<OrderBill> findByStatusAndActive(int id, boolean active);
}
