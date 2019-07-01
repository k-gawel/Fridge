package org.california.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.california.controller.service.ContainerControllerService;
import org.california.model.transfer.request.ContainerForm;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin
public class ContainerController {

    private final ContainerControllerService containerControllerService;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    public ContainerController(ContainerControllerService containerControllerService) {
        this.containerControllerService = containerControllerService;
    }

    @PostMapping(value = "/containers", consumes = "application/json")
    public ResponseEntity addNewContainer(
            @RequestHeader("token") String token,
            @Valid @RequestBody ContainerForm containerForm)
    {
        Object result;
        HttpStatus httpStatus;

        try {
            result = containerControllerService.newContainer(token, containerForm);
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


    @GetMapping("/containers")
    public ResponseEntity get(
            @RequestHeader("token") String token,
            @RequestParam(value = "ids", defaultValue = "") String ids,
            @RequestParam(value = "placeIds", defaultValue = "") String placeIds
    ) {

        Object result;
        HttpStatus httpStatus;

        try {
            result = containerControllerService.get(token, ids, placeIds);
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


    @GetMapping(value = "/containers/user_stats", consumes = "application/json")
    public ResponseEntity getUserStats(
            @RequestHeader("token") String token,
            @RequestParam(name = "user_ids", defaultValue = "") String userIdsString,
            @RequestParam(name = "place_ids", defaultValue = "") String placeIdsString,
            @RequestParam(name = "container_ids", defaultValue = "") String containerIdsString
    ) {

        logger.info("getUserStats token: {}, user_ids: {}, place_ids: {}, container_ids: {}",
                    token, userIdsString, placeIdsString, containerIdsString);

        Object result;
        HttpStatus httpStatus;

        try {
            result = containerControllerService.getUserStats(token, userIdsString, placeIdsString, containerIdsString);
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
