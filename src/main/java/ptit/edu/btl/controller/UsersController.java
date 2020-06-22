package ptit.edu.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.service.EmailService;
import ptit.edu.btl.service.NotificationService;
import ptit.edu.btl.service.UsersService;
import ptit.edu.btl.util.ResponseJson;

@RestController
@RequestMapping("/users")
public class UsersController extends BaseController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/create")
    ResponseEntity<ResponseJson> createUser(@RequestBody Users users) throws Exception {
        try {
            emailService.sendMail(users.getPeople().getEmail(), "Craete user", "Tạo tài khoản thành công");
            return createSuccessResponse(usersService.create(users), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @PutMapping("/update")
    ResponseEntity<ResponseJson> updateUser(@RequestBody Users users) throws Exception {
        try {
            return createSuccessResponse(usersService.update(users), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @DeleteMapping("/delete")
    ResponseEntity<ResponseJson> deleteUserById(@RequestParam int id) throws Exception {
        try {
            usersService.delete(id);
            return createSuccessResponse("xoá thành công", HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @PostMapping("/update-fcm")
    ResponseEntity<ResponseJson> updateFCM(Authentication authentication, @RequestParam String token) throws Exception {
        try {
            Users users = usersService.findByUsername(authentication.getName());
            users.setTokenFCM(token);
            return createSuccessResponse(usersService.update(users), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }


}
