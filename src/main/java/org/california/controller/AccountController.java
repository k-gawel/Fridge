package org.california.controller;

import org.california.controller.service.AccountControllerService;
import org.california.model.transfer.request.AccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AccountController {

    private AccountControllerService accountControllerService;

    @Autowired
    public AccountController(AccountControllerService accountControllerService) {
        this.accountControllerService = accountControllerService;
    }


    @PostMapping("/account/newAccount")
    public ResponseEntity newAccount(
            @RequestBody AccountForm form
    ) {
        Object result;
        HttpStatus httpStatus;

        try {
            result = accountControllerService.newAccount(form);
            httpStatus = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            e.printStackTrace();
            result = e;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }


    @PutMapping("/account/changeAccountDetails")
    public ResponseEntity changeAccountDetails(
            @RequestHeader("token") String token,
            @RequestHeader("password") String password,
            @RequestBody AccountForm accountForm

    ) {
        Object result;
        HttpStatus httpStatus;

        try {
            result = accountControllerService.changeAccountDetails(token, password, accountForm);
            httpStatus = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            e.printStackTrace();
            result = e;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }


    @GetMapping("/account/search")
    public ResponseEntity searchAccountByName(
            @RequestHeader("token") String token,
            @RequestParam(name = "name", defaultValue = "") String name
    ) {

        Object result;
        HttpStatus httpStatus;

        try {
            result = accountControllerService.searchByName(token, name);
            httpStatus = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            e.printStackTrace();
            result = e;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }


}
