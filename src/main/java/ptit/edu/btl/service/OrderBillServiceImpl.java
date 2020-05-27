package ptit.edu.btl.service;

import org.springframework.stereotype.Service;
import ptit.edu.btl.constant.Constant;
import ptit.edu.btl.entity.Address;
import ptit.edu.btl.entity.CartDetail;
import ptit.edu.btl.entity.OrderBill;
import ptit.edu.btl.entity.Payment;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.repository.CartDetailRepository;
import ptit.edu.btl.repository.OrderBillRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderBillServiceImpl implements OrderBillService {

    private final OrderBillRepository orderBillRepository;
    private final CartDetailRepository cartDetailRepository;

    public OrderBillServiceImpl(OrderBillRepository orderBillRepository, CartDetailRepository cartDetailRepository) {
        this.orderBillRepository = orderBillRepository;
        this.cartDetailRepository = cartDetailRepository;
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
        Address address = entity.getAddress();
        if(address != null){
            // address.setFiActivate(true);
            // address.setOrderBill(entity)
            // addressReponsitory.save(address);
        }
        Payment payment = entity.getPayment();
        if(payment != null){
            // payment.setFiActivate(true);
            // payment.setOrderBill(entity)
            // paymentReponsitory.save(address);
        }
        List<CartDetail> cartDetailList = entity.getCartDetailList();
        for (CartDetail cartDetail :  cartDetailList) {
            cartDetail.setActive(true);
//            cartDetail.setOrderBillId(entity.getId());
            if (cartDetail.getNumber() > 0)
                cartDetailRepository.save(cartDetail);
//            else
//                cartDetailList.remove(cartDetail);
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
