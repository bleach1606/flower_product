package ptit.edu.btl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import ptit.edu.btl.util.ResponseJson;

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BaseController {
    public ResponseEntity<ResponseJson> createResponse(Object obj, boolean isSuccess,
                                                       String errorMessage, HttpStatus httpStatus) {
        ResponseJson objResponse = new ResponseJson();
        objResponse.setData(obj);
        objResponse.setSuccess(isSuccess);
        objResponse.setMessage(errorMessage);
        return new ResponseEntity<>(objResponse, httpStatus);
    }

    public ResponseEntity<ResponseJson> createSuccessResponse(Object obj, HttpStatus httpStatus) {
        return createResponse(obj, true, "", httpStatus);
    }

    public ResponseEntity<ResponseJson> createErrorResponse(Object obj, String errorMessage, HttpStatus httpStatus) {
        return createResponse(obj, false, errorMessage, httpStatus);
    }

    public ResponseEntity<ResponseJson> createErrorResponse(String errorMessage, HttpStatus httpStatus) {
        return createResponse(null, false, errorMessage, httpStatus);
    }

}
