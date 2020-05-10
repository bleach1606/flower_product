package ptit.edu.btl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.repository.UsersRepository;
import ptit.edu.btl.service.UsersService;

@SpringBootApplication
public class BtlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtlApplication.class, args);
	}

}
