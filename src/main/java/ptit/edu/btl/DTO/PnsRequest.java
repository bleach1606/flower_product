package ptit.edu.btl.DTO;

import lombok.Data;

@Data
public class PnsRequest {
    private String fcmToken;
    private String title;
    private String content;

    public PnsRequest(String fcmToken, String title, String content) {
        this.fcmToken = fcmToken;
        this.title = title;
        this.content = content;
    }
}
