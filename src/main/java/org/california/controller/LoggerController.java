package org.california.controller;

import org.california.controller.service.LoggerControllerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity getInstancesLogsByPlace(@RequestHeader("token") String token,
                                                  @RequestParam(name = "container_ids", defaultValue = "") String containerIdsString,
                                                  @RequestParam(name = "place_ids", defaultValue = "") String placeIdsString,
                                                  @RequestParam(name = "limit", defaultValue = "20") int limit) {

        var result = controllerService.getInstancesChangesByPlace(token, placeIdsString, containerIdsString, limit);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


}
