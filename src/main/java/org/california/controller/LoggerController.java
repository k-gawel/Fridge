package org.california.controller;

import org.california.controller.service.LoggerControllerService;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.repository.utils.OffsetLimit;
import org.california.service.serialization.annotations.ByIds;
import org.california.service.serialization.annotations.ByToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController("/logs")
@RequestMapping("/logs")
@CrossOrigin
public class LoggerController extends BaseController {

    private LoggerControllerService controllerService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    public LoggerController(LoggerControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping("/instances")
    public ResponseEntity getInstancesLogsByPlace(@ByToken Account account,
                                                  @ByIds(entity = Container.class) Collection<Container> containers,
                                                  @ByIds(entity = Place.class) Collection<Place> places,
                                                  OffsetLimit offsetLimit) {

        var result = controllerService.getInstancesChangesByPlace(account, containers, places, offsetLimit);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


}
