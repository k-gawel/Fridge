package org.california.controller;

import org.california.controller.service.ContainerControllerService;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.model.transfer.request.forms.ContainerForm;
import org.california.service.serialization.annotations.ByIds;
import org.california.service.serialization.annotations.ByToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController("/containers")
@RequestMapping("/containers")
@CrossOrigin
public class ContainerController extends BaseController {

    private final ContainerControllerService controllerService;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    public ContainerController(ContainerControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity addNewContainer(@ByToken Account account,
                                          @Valid @RequestBody ContainerForm containerForm) {

        var result = controllerService.newContainer(account, containerForm);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }


    @GetMapping
    public ResponseEntity get(@ByToken Account account,
                              @ByIds(entity = Container.class) Collection<Container> containers,
                              @ByIds(entity = Place.class) Collection<Place> places,
                              @ByIds(entity = Account.class) Collection<Account> users) {

        var result = controllerService.get(account, containers, places, users);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }


    @GetMapping(value = "/user_stats", consumes = "application/json")
    public ResponseEntity getUserStats(@ByToken Account account,
                                       @ByIds(entity = Account.class) Collection<Account> users,
                                       @ByIds(entity = Place.class) Collection<Place> places,
                                       @ByIds(entity = Container.class) Collection<Container> containers) {

        var result = controllerService.getUserStats(account, users, places, containers);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


}
