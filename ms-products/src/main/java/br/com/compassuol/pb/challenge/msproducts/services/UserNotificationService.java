package br.com.compassuol.pb.challenge.msproducts.services;

public interface UserNotificationService {
    void sendNewUserNotification(String email, String firstName);
    void sendUserModifiedNotification(String email, String firstName);
}
