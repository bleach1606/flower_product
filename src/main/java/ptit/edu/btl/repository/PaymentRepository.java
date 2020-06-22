package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.btl.entity.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
