package ptit.edu.btl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.edu.btl.constant.Constant;
import ptit.edu.btl.entity.Address;
import ptit.edu.btl.entity.CartDetail;
import ptit.edu.btl.entity.OrderBill;
import ptit.edu.btl.entity.Payment;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.repository.AddressRepository;
import ptit.edu.btl.repository.CartDetailRepository;
import ptit.edu.btl.repository.OrderBillRepository;
import ptit.edu.btl.repository.PaymentRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderBillServiceImpl implements OrderBillService {

    private final OrderBillRepository orderBillRepository;
    private final CartDetailRepository cartDetailRepository;
    @Autowired
    AddressRepository repositoryAddress;
    @Autowired
    PaymentRepository repositoryPayment;

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
        if(entity.getStatus() == Constant.OrderStatus.WAIT.getId()){
            entity.setOrderDate(new Date());
        }
        Address address = (Address) entity.getAddress();
        if(address != null){
             address.setActive(true);
             repositoryAddress.save(address);
        }
        Payment payment = (Payment) entity.getPayment();
        if(payment != null){
            if(payment.getUsers()==null)
                payment.setUsers(entity.getUsers());
             payment.setActive(true);
             repositoryPayment.save(payment);
        }
        List<CartDetail> cartDetailList = entity.getCartDetailList();
        for (CartDetail cartDetail :  cartDetailList) {
            cartDetail.setActive(true);
//            cartDetail.setOrderBillId(entity.getId());
            if (cartDetail.getNumber() > 0)
                cartDetailRepository.save(cartDetail);
            else
                cartDetailList.remove(cartDetail);
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
