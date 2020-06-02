package ptit.edu.btl.DTO;

import lombok.Data;

@Data
public class PnsRequest {
    private String fcmToken;
    private String title;
    private String content;
}
