package br.com.compassuol.pb.challenge.msnotification.constants;

public class EmailConstants {

    public static final String EMAIL_NAME = "COMPANY";
    public static final String EMAIL_SENDER = "notification@do-not-reply.company.com";

    public static final String EMAIL_SUBJECT_NEW_USER = "Welcome to Company";

    public static final String EMAIL_BODY_NEW_USER = "<<html><body><p>Hi, %s</p><p>Welcome to Company!</p><p>Thank you for joining us.</p><p>Best regards,</p><p>Company Team</p></body></html>";

    public static final String EMAIL_SUBJECT_USER_MODIFIED = "Your account has been modified!";

    public static final String EMAIL_BODY_USER_MODIFIED = "<html><body><p>Hi, %s</p><p>Your account has been modified!</p><p>Best regards,</p><p>Company Team</p></body></html>";

    public static final String EMAIL_CONTENT_TYPE_HTML = "text/html";

    private EmailConstants() {
    }
}
