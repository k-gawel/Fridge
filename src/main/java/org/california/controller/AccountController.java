package org.california.controller;

import org.california.controller.service.AccountControllerService;
import org.california.model.transfer.request.forms.AccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController("/accounts")
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController extends BaseController {

    private final AccountControllerService controllerService;

    @Autowired
    public AccountController(AccountControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @PostMapping
    public ResponseEntity newAccount(@Valid @RequestBody AccountForm form) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.newAccount(form);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }

        return ResponseEntity.status(status).body(result);
    }


    @PutMapping
    public ResponseEntity changeAccountDetails(@RequestHeader("token") String token,
                                               @RequestHeader("password") String password,
                                               @Valid @RequestBody AccountForm accountForm) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.changeAccountDetails(token, password, accountForm);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }

        return ResponseEntity.status(status).body(result);
    }


    @GetMapping
    public ResponseEntity searchAccountByName(
            @RequestHeader("token") String token,
            @RequestParam(name = "name", defaultValue = "") String name) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.searchByName(token, name);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }

        return ResponseEntity.status(status).body(result);
    }


}
