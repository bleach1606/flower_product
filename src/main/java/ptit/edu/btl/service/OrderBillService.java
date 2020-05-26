package ptit.edu.btl.service;

import ptit.edu.btl.entity.Order;
import ptit.edu.btl.exception.BTLException;

import java.util.List;
import java.util.Optional;

public interface OrderBillService {

    Order create(Order entity) throws BTLException;

    Optional<Order> findById(int id) throws BTLException;

    Order update(Order entity) throws BTLException;

    List<Order> findALl() throws BTLException;

    List<Order> findByUsers_idAndActive(int id, boolean bo);

    void deleteOrderBill(Order entity) throws  BTLException;

    Order findFirstByUsers_idAndStatusAndActive(int id, int status, boolean active);
    void delete(int id) throws BTLException;
}
