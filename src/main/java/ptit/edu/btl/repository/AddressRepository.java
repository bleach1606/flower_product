package ptit.edu.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.edu.btl.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
