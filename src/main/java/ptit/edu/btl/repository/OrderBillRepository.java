package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.edu.btl.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderBillRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUsers_idAndActive(int id, boolean active);
    List<Order> findByUsers_idAndStatusAndActive(int id, int status, boolean active);
    Optional<Order> findFirstByUsers_idAndStatusAndActive(int id, int status, boolean active);
}
