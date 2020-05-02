package ptit.edu.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ptit.edu.btl.entity.Token;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.repository.TokenRepository;
import ptit.edu.btl.repository.UsersRepository;
import ptit.edu.btl.util.ResponseJson;
import ptit.edu.btl.controller.BaseController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/public")
public class PublicController extends BaseController {
    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/create-token")
    public ResponseEntity<Token> createToken(){
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setExpiresAt(new Date());

        return ResponseEntity.ok(tokenRepository.save(token));
    }

    @PostMapping("/login")
    ResponseEntity<ResponseJson> login(@RequestBody Users users) throws Exception{

        try {
            Users user = usersRepository.findByUsernameAndAndPassword(users.getUsername(), users.getPassword());
            Token token = new Token();
            token.setToken(UUID.randomUUID().toString());
            token.setExpiresAt(new Date());
            tokenRepository.save(token);
            user.setToken(token);
            System.out.println(users.toString());
            return createSuccessResponse(user, HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.resolve(403));
        }
    }

    @PostMapping("/upload")
    public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get("" + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }
}
