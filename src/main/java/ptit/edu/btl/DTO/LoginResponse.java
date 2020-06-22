package ptit.edu.btl.DTO;

import lombok.Data;
import ptit.edu.btl.entity.Users;
@Data
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Users user;

    public LoginResponse(String accessToken, Users user) {
        this.accessToken = accessToken;
        this.user = user;
    }
}
