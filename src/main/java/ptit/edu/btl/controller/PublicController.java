package ptit.edu.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ptit.edu.btl.entity.Token;
import ptit.edu.btl.repository.TokenRepository;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    TokenRepository tokenRepository;

    @GetMapping("/create-token")
    public ResponseEntity<Token> createToken(){
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setExpiresAt(new Date());

        return ResponseEntity.ok(tokenRepository.save(token));
    }
}
