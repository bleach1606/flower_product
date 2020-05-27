package ptit.edu.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptit.edu.btl.entity.Category;
import ptit.edu.btl.entity.FilterForm;
import ptit.edu.btl.entity.FlowerProducts;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.repository.FlowerProductsRepository;
import ptit.edu.btl.service.FlowerProductsService;
import ptit.edu.btl.util.ResponseJson;

@RestController
@RequestMapping("/flower-products")
public class FlowerProductsController extends BaseController {

    private final FlowerProductsService flowerProductsService;
    @Autowired
    private FlowerProductsRepository productsRepository;

    public FlowerProductsController(FlowerProductsService flowerProductsService) {
        this.flowerProductsService = flowerProductsService;
    }

    @PostMapping("/create")
    ResponseEntity<ResponseJson> createUser(@RequestBody FlowerProducts flowerProducts) throws Exception {
        try {
            return createSuccessResponse(flowerProductsService.create(flowerProducts), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @PutMapping("/update")
    ResponseEntity<ResponseJson> updateUser(@RequestBody FlowerProducts flowerProducts) throws Exception {
        try {
            return createSuccessResponse(flowerProductsService.update(flowerProducts), HttpStatus.OK);
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
            flowerProductsService.delete(id);
            return createSuccessResponse("xoá thành công", HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @PostMapping("find-by-name")
    ResponseEntity<ResponseJson> findByName(@RequestBody FilterForm filterForm) throws Exception {
        try {
            return createSuccessResponse(flowerProductsService.findByName(filterForm), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @PostMapping("find-by-category")
    ResponseEntity<ResponseJson> findByCategory(@RequestBody Category entity) throws Exception {
        try {
            return createSuccessResponse(productsRepository.findByCategoryIdOrderByName(entity.getId()), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }
}
