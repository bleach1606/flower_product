package ptit.edu.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ptit.edu.btl.constant.Constant;
import ptit.edu.btl.entity.Notification;
import ptit.edu.btl.entity.OrderBill;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.repository.OrderBillRepository;
import ptit.edu.btl.service.EmailService;
import ptit.edu.btl.service.NotificationService;
import ptit.edu.btl.service.OrderBillService;
import ptit.edu.btl.service.UsersService;
import ptit.edu.btl.util.ResponseJson;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/orderbill")
public class OrderBillController extends BaseController{

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private OrderBillService orderBillService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/get-current-order")
    ResponseEntity<ResponseJson> findCurrentOrder(Authentication authentication) throws Exception {
        try {
            Users user = usersService.findByUsername(authentication.getName());
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
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @GetMapping("/get-order-list")
    ResponseEntity<ResponseJson> findListOrder(Authentication authentication) throws Exception {
        try {
            Users user = usersService.findByUsername(authentication.getName());
            return createSuccessResponse(orderBillService.findByUsers_idAndActive(user.getId(), true), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    //kịch bản gửi lên orderBill - gồm update trạng thái, thành phần all
    @PostMapping("/update-orderBill")
    ResponseEntity<ResponseJson> updateOrderBill(Authentication authentication, @RequestBody OrderBill orderBill) throws Exception {
        try {
            Users user = usersService.findByUsername(authentication.getName());
            orderBill.setUsers(user);
            orderBill = orderBillService.update(orderBill);
            if (orderBill.getStatus() == Constant.OrderStatus.WAIT.getId()) {
                orderBill = new OrderBill();
                orderBill.setUsers(user);
                orderBill = orderBillService.create(orderBill);
            }
            return createSuccessResponse(orderBill, HttpStatus.valueOf(200));
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @PutMapping("update-status/{id}")
    ResponseEntity<ResponseJson> updateStatus(Authentication authentication,
          @PathVariable("id") int id, @RequestParam int status) throws Exception {
        try {
            OrderBill orderBill = orderBillService.findById(id);
            orderBill.setStatus(status);
            return createSuccessResponse( orderBillService.update(orderBill), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @GetMapping()
    ResponseEntity<ResponseJson> listOrderBill(@RequestParam int status) throws Exception {
        try {
            List<OrderBill> orderBillList = orderBillService.findByStatusAndActive(status, true);
            return createSuccessResponse( orderBillList, HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }
}
