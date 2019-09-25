package org.california.controller;

import org.california.controller.service.ContainerControllerService;
import org.california.model.transfer.request.forms.ContainerForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity addNewContainer(@RequestHeader("token") String token,
                                          @Valid @RequestBody ContainerForm containerForm)
    {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.newContainer(token, containerForm);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }

        return ResponseEntity.status(status).body(result);
    }


    @GetMapping
    public ResponseEntity get(@RequestHeader("token") String token,
                              @RequestParam(value = "ids", defaultValue = "") String ids,
                              @RequestParam(value = "placeIds", defaultValue = "") String placeIds
    ) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.get(token, ids, placeIds);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }

        return ResponseEntity.status(status).body(result);
    }


    @GetMapping(value = "/user_stats", consumes = "application/json")
    public ResponseEntity getUserStats(@RequestHeader("token") String token,
                                       @RequestParam(name = "user_ids", defaultValue = "") String userIdsString,
                                       @RequestParam(name = "place_ids", defaultValue = "") String placeIdsString,
                                       @RequestParam(name = "container_ids", defaultValue = "") String containerIdsString
    ) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.getUserStats(token, userIdsString, placeIdsString, containerIdsString);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }

        return ResponseEntity.status(status).body(result);
    }


}
