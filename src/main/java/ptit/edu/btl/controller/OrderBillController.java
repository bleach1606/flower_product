package ptit.edu.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ptit.edu.btl.constant.Constant;
import ptit.edu.btl.entity.Order;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.service.EmailService;
import ptit.edu.btl.service.OrderBillService;
import ptit.edu.btl.service.UsersService;
import ptit.edu.btl.util.ResponseJson;

@RestController
@RequestMapping("/orderbill")
public class OrderBillController extends BaseController{

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private OrderBillService orderBillService;

    @GetMapping("/findOrder/{id}")
    ResponseEntity<ResponseJson> createUser(Authentication authentication) throws Exception {
        try {
            Users users = usersService.findByUsername(authentication.getName());
            Order order = orderBillService.findFirstByUsers_idAndStatusAndActive(users.getId(),
                    Constant.OrderStatus.NEW.getId(), true);
            if (order == null) {
                order = new Order();
//                orderBill.setUsers_id(users.getId());
                order.setUsers(users);
                order = orderBillService.create(order);
            }
            return createSuccessResponse(order, HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(500));
        }
    }

    //kịch bản gửi lên orderBill - gồm update trạng thái, thành phần all
    @PostMapping("/update-orderBill")
    ResponseEntity<ResponseJson> addItem(@RequestBody Order order) throws Exception {
        try {
            return createSuccessResponse(orderBillService.update(order), HttpStatus.valueOf(200));
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(500));
        }
    }

    //todo xác nhận đặt hàng - xác nhận vận chuyển - xác nhận lấy hàng thành công
}
