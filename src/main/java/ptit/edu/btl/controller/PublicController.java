package ptit.edu.btl.controller;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ptit.edu.btl.DTO.LoginResponse;
import ptit.edu.btl.entity.Notification;
import ptit.edu.btl.entity.People;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.jwt.JwtTokenProvider;
import ptit.edu.btl.repository.UsersRepository;
import ptit.edu.btl.service.EmailService;
import ptit.edu.btl.service.NotificationService;
import ptit.edu.btl.service.UsersService;
import ptit.edu.btl.session.CustomUserDetails;
import ptit.edu.btl.util.ResponseJson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/public")
public class PublicController extends BaseController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    EmailService emailService;

    @PostMapping("/login")
    ResponseEntity<ResponseJson> login(@RequestBody Users users) throws Exception{
        try {
                        // Xác thực từ username và password.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            users.getUsername(),
                            users.getPassword()
                    )
            );
            Users user = usersRepository.findByUsername(users.getUsername()).orElse(null);

            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Trả về jwt cho người dùng.
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            return createSuccessResponse(new LoginResponse(jwt, user), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.resolve(403));
        }
    }

    @PostMapping("/login-access")
    ResponseEntity<ResponseJson> loginFace(@RequestBody Users users) throws Exception{
        try {
            String url = "https://graph.facebook.com";
            String asscessToken = users.getAccessToken();
            FacebookClient client = new DefaultFacebookClient(asscessToken);
            User userFace = client.fetchObject("me", User.class);

            Users user = usersService.findByUsername(userFace.getId() + "_facebook");
            if ( user == null)  {
                user = createUser(users, userFace);
            }
            // Xác thực từ username và password.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            ""
                    )
            );

            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Trả về jwt cho người dùng.
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            return createSuccessResponse(new LoginResponse(jwt, user), HttpStatus.OK);
        } catch (Exception ex) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setSuccess(false);
            responseJson.setMessage(ex.getMessage());
            return createErrorResponse(ex.getMessage(), HttpStatus.resolve(403));
        }
    }

    @PostMapping("/signup")
    ResponseEntity<ResponseJson> signup(@RequestBody Users users) throws Exception{
        try {
            users = usersService.create(users);
            Notification notification = new Notification();
            notification.setContent("Chào mừng đến với CAMELIA !!!");
            notification.setDate(new Date());
            notification.setTitle("Thông báo.");
            notification.setUsers(users);
            notification.setAvatar("1");
            notificationService.create(notification);
            emailService.sendMail(users.getPeople().getEmail(), "Craete user",
                    "<h1> CAMELIA Thông báo </h1>" +
                            "<h3>Tạo tài khoản thành công<h3>");
            return createSuccessResponse(users, HttpStatus.OK);
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
        Path path = Paths.get( "images/" + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/public/download/")
                .path(fileName)
                .toUriString();
//        String link = "http://192.168.1.18:8080/public/download/" + fileName;
        return ResponseEntity.ok(fileDownloadUri);
    }

    @PostMapping("/multi-upload")
    public ResponseEntity multiUpload(@RequestParam("files") MultipartFile[] files) {
        List<Object> fileDownloadUrls = new ArrayList<>();
        Arrays.asList(files)
                .stream()
                .forEach(file -> fileDownloadUrls.add(uploadToLocalFileSystem(file).getBody()));
        return ResponseEntity.ok(fileDownloadUrls);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) throws IOException {

        Path path = Paths.get("images/" + fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String contentType = "image/jpeg";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + resource.getFilename() + "\"")
                .body(resource);
    }

    public Users createUser(Users users , User userFace) {
        try {
            users.setUsername(String.valueOf(userFace.getId() + "_facebook"));
            users.setPassword("");
            People people = new People();
            people.setAddress(userFace.getHometownName());
            people.setActive(true);
            people.setAvatar("1");
            people.setBirthday(userFace.getBirthdayAsDate());
            if (userFace.getFirstName() == null) {
                people.setFirstName(userFace.getName().split(" ")[0]);
                people.setLastName(userFace.getName().split(" ")[1]);
            } else {
                people.setFirstName(userFace.getFirstName());
                people.setLastName(userFace.getLastName());
            }

            people.setEmail(userFace.getEmail());
            users.setPeople(people);
            return usersService.create(users);
        } catch (BTLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
