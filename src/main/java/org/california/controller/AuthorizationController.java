package org.california.controller;

import org.california.controller.service.AuthorizationControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthorizationController {


    private AuthorizationControllerService authorizationControllerService;

    @Autowired
    public AuthorizationController(AuthorizationControllerService authorizationControllerService) {
        this.authorizationControllerService = authorizationControllerService;
    }


    @PostMapping("/auth/login")
    public ResponseEntity login(
            @RequestHeader(name = "token", defaultValue = "") String token,
            @RequestHeader(name = "username", defaultValue = "") String username,
            @RequestHeader(name = "password", defaultValue = "") String password
    ) {

        Object result;
        HttpStatus httpStatus;

        try {
            result = authorizationControllerService.login(token, username, password);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = e;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }


    @PostMapping("/auth/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader("token") String token) {
        return null;
    }

}
