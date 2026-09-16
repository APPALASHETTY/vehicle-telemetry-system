package com.telemetry.notification;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Get all notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Create notification
    public Notification createNotification(Notification notification) {

        // Notification Service must generate its own ID.
        // Ignore any ID received from Alert Service.
        notification.setId(null);

        return notificationRepository.save(notification);
    }

    // Get notification by ID
    public Notification getNotificationById(Long id) {

        return notificationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Notification not found with id: " + id
                        ));
    }

    // Delete notification
    public void deleteNotification(Long id) {

        if (!notificationRepository.existsById(id)) {
            throw new RuntimeException(
                    "Notification not found with id: " + id
            );
        }

        notificationRepository.deleteById(id);
    }
}