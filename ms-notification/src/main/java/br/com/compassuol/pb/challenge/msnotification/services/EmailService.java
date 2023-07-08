package br.com.compassuol.pb.challenge.msnotification.services;

import br.com.compassuol.pb.challenge.msnotification.entities.Email;

public interface EmailService {
    Email sendEmail(Email email);
}
