package ptit.edu.btl.service;

import org.springframework.stereotype.Service;
import ptit.edu.btl.entity.Notification;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.exception.BTLException;
import ptit.edu.btl.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification create(Notification entity) throws BTLException {
        entity.setCheck(false);
        return notificationRepository.save(entity);
    }

    @Override
    public Notification update(Notification entity) throws BTLException {
        return notificationRepository.save(entity);
    }

    @Override
    public List<Notification> findByUser(Users user) throws BTLException {
        return notificationRepository.findByUsersOrderByDateDesc(user);
    }

    @Override
    public Notification findById(int id) throws BTLException {
        return notificationRepository.findById(id).orElse(null);
    }
}
