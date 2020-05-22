package ptit.edu.btl.service;

import ptit.edu.btl.entity.Cart;
import ptit.edu.btl.exception.BTLException;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart create(Cart entity) throws BTLException;

    Optional<Cart> findById(int id) throws BTLException;

    Cart update(Cart entity) throws BTLException;

    List<Cart> findALl() throws BTLException;

    List<Cart> findByOrderId(int id) throws BTLException;

    void delete(int id) throws BTLException;
}
