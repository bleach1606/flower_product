package ptit.edu.btl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.edu.btl.DTO.PnsRequest;
import ptit.edu.btl.constant.Constant;
import ptit.edu.btl.entity.*;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.repository.AddressRepository;
import ptit.edu.btl.repository.CartDetailRepository;
import ptit.edu.btl.repository.OrderBillRepository;
import ptit.edu.btl.repository.PaymentRepository;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderBillServiceImpl implements OrderBillService {

    private final OrderBillRepository orderBillRepository;
    private final CartDetailRepository cartDetailRepository;

    @Autowired
    AddressRepository repositoryAddress;

    @Autowired
    PaymentRepository repositoryPayment;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private FCMService fcmService;

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
    public OrderBill findById(int id) throws BTLException {
        return orderBillRepository.findById(id).orElse(null);
    }

    @Override
    public OrderBill update(OrderBill entity) throws BTLException {
        if (entity.getId() < 1) {
            entity.setActive(true);
            entity.setStatus(Constant.OrderStatus.WAIT.getId());
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
            if (cartDetail.getNumber() > 0) {
                if ( entity.getId() > 0)
                cartDetail.setOrderBillId(entity.getId());
                cartDetailRepository.save(cartDetail);
            }
            else
                cartDetailList.remove(cartDetail);
        }


//        if (entity.getStatus() == Constant.OrderStatus.CONFIRM.getId()) {
//            emailService.sendMail(
//                    entity.getUsers().getPeople().getEmail(),
//                    "Thông báo: ",
//                    "Bạn đặt hàng thành công, gói hàng đã được giao đến vận chuyển.");
//        }
        seenNotification(entity.getStatus(), entity.getUsers());
        seenFCM(entity.getStatus(), entity.getUsers());
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
    public List<OrderBill> findByStatusAndActive(int id, boolean bo) throws BTLException {
        return orderBillRepository.findByStatusAndActive(id, bo);
    }

    @Override
    public void delete(int id) throws BTLException {
        orderBillRepository.deleteById(id);
    }

    public void seenFCM(int status, Users users) {
        if (users == null || users.getTokenFCM() == null) {
            return;
        }
        fcmService.pushNotification(new PnsRequest(
                users.getTokenFCM(), "CAMELIA thông báo:", Constant.OrderStatus.findById(status).getStatus()));
    }

    public void seenNotification(int status, Users users) {

        try {
            Notification notification = new Notification();
            notification.setContent(Constant.OrderStatus.findById(status).getStatus());
            notification.setDate(new Date());
            notification.setTitle("Thông báo.");
            notification.setUsers(users);
            Random rd = new Random();
            notification.setAvatar(String.valueOf(rd.nextInt(114)  + 1));
            notificationService.create(notification);
        } catch (BTLException e) {
            e.printStackTrace();
        }
    }
}
