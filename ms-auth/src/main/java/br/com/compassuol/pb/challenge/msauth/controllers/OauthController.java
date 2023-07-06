package br.com.compassuol.pb.challenge.msauth.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class OauthController {

    /**
     * {@link br.com.compassuol.pb.challenge.msauth.filters.JWTTokenGeneratorFilter} will handle this request
     */
    @PostMapping("token")
    public void getToken() {
    }


}
