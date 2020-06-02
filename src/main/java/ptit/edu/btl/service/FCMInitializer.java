package ptit.edu.btl.service;

import com.google.api.client.util.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
@Log4j
public class FCMInitializer {
//    @Value("${app.firebase-configuration-file}")
//    private String firebaseConfigPath;
    private String firebaseConfigPath = "/resources/gg.json";

    @Autowired
    ResourceLoader resourceLoader;

    @PostConstruct
    public void initialize() {
        log.info("Start init");
        try {

            Resource resource = resourceLoader.getResource("classpath:gg.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(resource.getInputStream())).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase application has been initialized");
            } else {
                System.out.println("Firebase app: " + FirebaseApp.getApps().size() );
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}