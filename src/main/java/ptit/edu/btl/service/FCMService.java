package ptit.edu.btl.service;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;
import ptit.edu.btl.DTO.PnsRequest;

@Service
public class FCMService {

    public String pushNotification(PnsRequest pnsRequest) {
        Message message = Message.builder()
                .putData("content", pnsRequest.getContent())
                .setToken(pnsRequest.getFcmToken())
                .build();

        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(androidMessage());
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Message androidMessage() {
        // [START android_message]
        Message message = Message.builder()
                .setAndroidConfig(AndroidConfig.builder()
                        .setTtl(3600 * 1000) // 1 hour in milliseconds
                        .setPriority(AndroidConfig.Priority.NORMAL)
                        .setNotification(AndroidNotification.builder()
                                .setTitle("$GOOG up 1.43% on the day")
                                .setBody("$GOOG gained 11.80 points to close at 835.67, up 1.43% on the day.")
                                .setIcon("stock_ticker_update")
                                .setColor("#f45342")
                                .build())
                        .build())
                .setTopic("industry-tech")
                .build();
        // [END android_message]
        return message;
    }
}
