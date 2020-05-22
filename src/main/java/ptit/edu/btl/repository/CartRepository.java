package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.edu.btl.entity.Cart;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByOrderId(int OrderId);
}
