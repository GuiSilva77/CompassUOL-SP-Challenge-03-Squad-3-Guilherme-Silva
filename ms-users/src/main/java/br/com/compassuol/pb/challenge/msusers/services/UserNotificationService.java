package br.com.compassuol.pb.challenge.msusers.services;

public interface UserNotificationService {
    void sendNewUserNotification(String email, String firstName);
    void sendUserModifiedNotification(String email, String firstName);
}
