package ptit.edu.btl.DTO;

import lombok.Data;
import ptit.edu.btl.entity.Users;
@Data
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Users users;

    public LoginResponse(String accessToken, Users users) {
        this.accessToken = accessToken;
        this.users = users;
    }
}
