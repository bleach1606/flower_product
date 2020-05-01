package ptit.edu.btl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.service.UsersService;
import ptit.edu.btl.util.ResponseJson;

@RestController
@RequestMapping("/users")
public class UsersController extends BaseController{

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/create")
    ResponseEntity<ResponseJson> createUser(@RequestBody Users users) throws Exception {
        try {
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
}
