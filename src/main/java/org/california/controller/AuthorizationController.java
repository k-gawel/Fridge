package org.california.controller;

import org.california.controller.service.AuthorizationControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/auth")
@RequestMapping("/auth")
@CrossOrigin
public class AuthorizationController extends BaseController {


    private final AuthorizationControllerService controllerService;

    @Autowired
    public AuthorizationController(AuthorizationControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestHeader(name = "token", defaultValue = "") String token,
                                @RequestHeader(name = "username", defaultValue = "") String username,
                                @RequestHeader(name = "password", defaultValue = "") String password) {

        var result = controllerService.login(token, username, password);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader("token") String token) {
        return null;
    }

}
