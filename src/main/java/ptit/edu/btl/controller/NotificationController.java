package ptit.edu.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ptit.edu.btl.DTO.PnsRequest;
import ptit.edu.btl.entity.Notification;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.service.FCMService;
import ptit.edu.btl.service.NotificationService;
import ptit.edu.btl.service.UsersService;
import ptit.edu.btl.util.ResponseJson;

@RestController
@RequestMapping("/notification")
public class NotificationController extends BaseController {
    @Autowired
    private FCMService fcmService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/push")
    public String sendSampleNotification(@RequestBody PnsRequest pnsRequest) {
        return fcmService.pushNotification(pnsRequest);
    }

    @GetMapping()
    ResponseEntity<ResponseJson> getNotification(Authentication authentication) throws Exception {
        try {
            Users user = usersService.findByUsername(authentication.getName());
            return createSuccessResponse(notificationService.findByUser(user), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @PutMapping("seen")
    ResponseEntity<ResponseJson> seenNotification(@RequestParam int id) throws Exception {
        try {
            Notification notification = notificationService.findById(id);
            if (notification == null) throw new Exception("Không tồn tại thông báo !!!");
            notification.setCheck(true);
            return createSuccessResponse(notificationService.update(notification), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

}
