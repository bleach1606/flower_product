package ptit.edu.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptit.edu.btl.entity.Comment;
import ptit.edu.btl.entity.FlowerProducts;
import ptit.edu.btl.repository.CommentRepository;
import ptit.edu.btl.util.ResponseJson;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {
    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/get_comment")
    ResponseEntity<ResponseJson> getComment(@RequestBody FlowerProducts products) throws Exception {
        try {
            // get comment by product_id
            List<Comment> listComment = commentRepository.findByFlowerProductsId(products.getId());
            return createSuccessResponse(listComment, HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PostMapping("/create")
    ResponseEntity<ResponseJson> createComment(@RequestBody Comment comment) throws Exception {
        try {
            comment.setActive(true);
            comment.setTime((new Date()).getTime());
            commentRepository.save(comment);
            return createSuccessResponse(null, HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.valueOf(400));
        }
    }
}

