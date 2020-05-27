package ptit.edu.btl.service;

import ptit.edu.btl.entity.OrderBill;
import ptit.edu.btl.exception.BTLException;

import java.util.List;
import java.util.Optional;

public interface OrderBillService {

    OrderBill create(OrderBill entity) throws BTLException;

    Optional<OrderBill> findById(int id) throws BTLException;

    OrderBill update(OrderBill entity) throws BTLException;

    List<OrderBill> findALl() throws BTLException;

    List<OrderBill> findByUsers_idAndActive(int id, boolean bo);

    void deleteOrderBill(OrderBill entity) throws  BTLException;

    OrderBill findFirstByUsers_idAndStatusAndActive(int id, int status, boolean active);


    void delete(int id) throws BTLException;
}
