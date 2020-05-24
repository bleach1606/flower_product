package ptit.edu.btl.service;

import org.springframework.stereotype.Service;
import ptit.edu.btl.constant.Constant;
import ptit.edu.btl.entity.Cart;
import ptit.edu.btl.entity.OrderBill;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.repository.CartRepository;
import ptit.edu.btl.repository.OrderBillRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderBillServiceImpl implements OrderBillService {

    private final OrderBillRepository orderBillRepository;
    private final CartRepository cartRepository;

    public OrderBillServiceImpl(OrderBillRepository orderBillRepository, CartRepository cartRepository) {
        this.orderBillRepository = orderBillRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public OrderBill create(OrderBill entity) throws BTLException {
        entity.setActive(true);
        entity.setStatus(Constant.OrderStatus.NEW.getId());
        return orderBillRepository.save(entity);
    }

    @Override
    public Optional<OrderBill> findById(int id) throws BTLException {
        return orderBillRepository.findById(id);
    }

    @Override
    public OrderBill update(OrderBill entity) throws BTLException {
        for (Cart cart : entity.getCartList() ) {
            cart.setOrderId(entity.getId());
            if (cart.getNumber() > 0)
                cartRepository.save(cart);
            else
                cartRepository.delete(cart);
        }
        return orderBillRepository.save(entity);
    }

    @Override
    public List<OrderBill> findALl() throws BTLException {
        return orderBillRepository.findAll();
    }

    @Override
    public List<OrderBill> findByUsers_idAndActive(int id, boolean bo) {
        return orderBillRepository.findByUsers_idAndActive(id, bo);
    }

    @Override
    public void deleteOrderBill(OrderBill entity) throws BTLException {
        orderBillRepository.delete(entity);
    }

    @Override
    public OrderBill findFirstByUsers_idAndStatusAndActive(int id, int status, boolean active) {
        return orderBillRepository.findFirstByUsers_idAndStatusAndActive(id, status, active).orElse(null);
    }

    @Override
    public void delete(int id) throws BTLException {
        orderBillRepository.deleteById(id);
    }
}
