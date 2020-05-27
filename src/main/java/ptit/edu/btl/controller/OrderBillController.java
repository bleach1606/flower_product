package ptit.edu.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ptit.edu.btl.constant.Constant;
import ptit.edu.btl.entity.OrderBill;
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

    @GetMapping("/findOrder")
    ResponseEntity<ResponseJson> findOrder(@RequestBody Users user) throws Exception {
        try {
            OrderBill orderBill = orderBillService.findFirstByUsers_idAndStatusAndActive(user.getId(),
                    Constant.OrderStatus.NEW.getId(), true);
            if (orderBill == null) {
                orderBill = new OrderBill();
                orderBill.setUsers(user);
                orderBill = orderBillService.create(orderBill);
            }
            return createSuccessResponse(orderBill, HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(500));
        }
    }

    //kịch bản gửi lên orderBill - gồm update trạng thái, thành phần all
    @PostMapping("/update-orderBill")
    ResponseEntity<ResponseJson> addItem(@RequestBody OrderBill orderBill) throws Exception {
        try {
            return createSuccessResponse(orderBillService.update(orderBill), HttpStatus.valueOf(200));
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(500));
        }
    }


}
