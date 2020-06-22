package ptit.edu.btl.service;

import ptit.edu.btl.entity.CartDetail;
import ptit.edu.btl.exception.BTLException;

import java.util.List;
import java.util.Optional;

public interface CartService {
    CartDetail create(CartDetail entity) throws BTLException;

    Optional<CartDetail> findById(int id) throws BTLException;

    CartDetail update(CartDetail entity) throws BTLException;

    List<CartDetail> findALl() throws BTLException;

    List<CartDetail> findByOrderId(int id) throws BTLException;

    void delete(int id) throws BTLException;
}
