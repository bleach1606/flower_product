package ptit.edu.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.service.EmailService;
import ptit.edu.btl.service.UsersService;
import ptit.edu.btl.util.ResponseJson;

@RestController
@RequestMapping("/users")
public class UsersController extends BaseController{

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsersService usersService;


    @PostMapping("/create")
    ResponseEntity<ResponseJson> createUser(Authentication authentication, @RequestBody Users users) throws Exception {
        try {
            System.out.println(authentication.getName());
//            emailService.sendMail(users.getPeople().getEmail(), "Craete user", "Tạo tài khoản thành công");
//            return createSuccessResponse(usersService.create(users), HttpStatus.OK);
            return createSuccessResponse(users, HttpStatus.OK);
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
}
