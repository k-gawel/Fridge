package org.california.controller;

import org.california.controller.service.LoggerControllerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController("/logs")
@CrossOrigin
public class LoggerController {

    private LoggerControllerService loggerControllerService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    public LoggerController(LoggerControllerService loggerControllerService) {
        this.loggerControllerService = loggerControllerService;
    }

    @GetMapping("/instances")
    public ResponseEntity getInstancesLogsByPlace(@RequestHeader("token") String token,
                                                  @RequestParam(name = "container_ids", defaultValue = "") String containerIdsString,
                                                  @RequestParam(name = "place_ids", defaultValue = "") String placeIdsString,
                                                  @RequestParam(name = "limit", defaultValue = "20") int limit) {

        Object result;
        HttpStatus httpStatus;

        try {
            result = loggerControllerService.getInstancesChangesByPlace(token, placeIdsString, containerIdsString, limit);
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


}
