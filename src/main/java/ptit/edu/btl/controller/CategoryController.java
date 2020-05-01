package ptit.edu.btl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptit.edu.btl.entity.Category;
import ptit.edu.btl.service.CategoryService;
import ptit.edu.btl.util.ResponseJson;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/create")
    ResponseEntity<ResponseJson> createUser(@RequestBody Category category) throws Exception {
        try {
            return createSuccessResponse(categoryService.create(category), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @PutMapping("/update")
    ResponseEntity<ResponseJson> updateUser(@RequestBody Category category) throws Exception {
        try {
            return createSuccessResponse(categoryService.update(category), HttpStatus.OK);
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
            categoryService.delete(id);
            return createSuccessResponse("xoá thành công", HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @GetMapping("/find-all")
    ResponseEntity<ResponseJson> findAll() throws Exception {
        try {
            return createSuccessResponse(categoryService.findALl(), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @GetMapping("/find-by-id")
    ResponseEntity<ResponseJson> findById(@RequestParam int id) throws Exception {
        try {
            return createSuccessResponse(categoryService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

}
