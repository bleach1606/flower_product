package ptit.edu.btl.service;

import ptit.edu.btl.entity.Notification;
import ptit.edu.btl.entity.Users;
import ptit.edu.btl.exception.BTLException;

import java.util.List;

public interface NotificationService {
    Notification create(Notification entity) throws BTLException;
    Notification update(Notification entity) throws BTLException;
    List<Notification> findByUser(Users user) throws BTLException;
    Notification findById(int id) throws BTLException;
}
