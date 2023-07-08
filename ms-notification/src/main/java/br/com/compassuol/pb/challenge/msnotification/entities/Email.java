package br.com.compassuol.pb.challenge.msnotification.entities;

import br.com.compassuol.pb.challenge.msnotification.entities.dto.EmailDto;
import br.com.compassuol.pb.challenge.msnotification.entities.enums.EmailStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(
        name = "tb_email",
        schema = "challenge"
)
public class Email {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(max = 128)
    @Column(name = "from_email", nullable = false)
    private String fromEmail;

    @Size(max = 128)
    @Column(name = "from_name", nullable = false)
    private String fromName;

    @Size(max = 128)
    @Column(name = "reply_to", nullable = false)
    private String replyTo;

    @Size(max = 128)
    @Column(name = "to_email", nullable = false)
    private String to;

    @Size(max = 128)
    @Column(name = "email_subject", nullable = false)
    private String subject;

    @Column(name = "email_body", nullable = false, columnDefinition = "TEXT")
    private String body;

    @Size(max = 128)
    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "email_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmailStatus status;

    @Column(name = "send_date", nullable = false)
    private LocalDateTime sendDate;

    public Email() {
    }

    public Email(String fromEmail, String fromName, String replyTo, String to, String subject, String body, String contentType) {
        this.fromEmail = fromEmail;
        this.fromName = fromName;
        this.replyTo = replyTo;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public EmailStatus getStatus() {
        return status;
    }

    public void setStatus(EmailStatus status) {
        this.status = status;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public EmailDto toDto() {
        return new EmailDto(this.fromEmail, this.fromName, this.replyTo, this.to, this.subject, this.body, this.contentType);
    }
}
