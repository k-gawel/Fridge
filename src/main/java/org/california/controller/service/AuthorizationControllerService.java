package org.california.controller.service;

import lombok.Builder;
import org.california.model.transfer.response.InitialResponse;
import org.california.service.model.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationControllerService {

    private final AuthorizationService authorizationService;

    @Autowired
    public AuthorizationControllerService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }


    public InitialResponse login(String token, String username, String password) {
        return token.equals("") ?
                authorizationService.login(username, password) : authorizationService.login(token);
    }


    public boolean logout(String token) {
        try {
            authorizationService.logout(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
