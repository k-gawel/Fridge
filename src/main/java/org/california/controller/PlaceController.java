package org.california.controller;

import org.california.controller.service.PlaceControllerService;
import org.california.model.transfer.request.PlaceForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PlaceController {

    private PlaceControllerService placeControllerService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlaceController(PlaceControllerService placeControllerService) {
        this.placeControllerService = placeControllerService;
    }


    @PostMapping("/place/new")
    public ResponseEntity newPlace(
            @RequestHeader("token") String token,
            @RequestBody PlaceForm placeForm
    ) {

        logger.info("%M token: {}, placeForm: {}", token, placeForm);

        Object result;
        HttpStatus httpStatus;

        try {
            result = placeControllerService.newPlace(token, placeForm);
            httpStatus = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = e;
            e.printStackTrace();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }


    @GetMapping("/place/get")
    public ResponseEntity get(
            @RequestHeader("token") String token,
            @RequestParam(name = "ids", defaultValue = "") String placeIdsString
    ) {

        logger.info("%M token: {}, ids: {}", token, placeIdsString);

        Object result;
        HttpStatus httpStatus;

        try {
            result = placeControllerService.get(token, placeIdsString);
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


    @PutMapping("/place/change_admin")
    public ResponseEntity changeAdmin(
            @RequestHeader("token") String token,
            @RequestParam(name = "place_id") Long placeId,
            @RequestParam(name = "new_admin_id") Long newAdminId
    ) {

        logger.info("%M token: {}, placeId: {}, newAdminId: {}", token, placeId, newAdminId);

        Object result;
        HttpStatus httpStatus;

        try {
            result = placeControllerService.changeAdmin(token, placeId, newAdminId);
            httpStatus = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            logger.error(e.toString());
            result = e;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }


        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }


    @DeleteMapping("/place/removeUser")
    public ResponseEntity removeUser(
            @RequestHeader("token") String token,
            @RequestParam("placeId") Long placeId,
            @RequestParam("userId") Long userId
    ) {

        logger.info("%M token: {}, placeId: {}, userId: {}", token, placeId, userId);

        Object result;
        HttpStatus httpStatus;

        try {
            result = placeControllerService.removeUserFromPlace(token, placeId, userId);
            httpStatus = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            logger.error(e.toString());
            result = e;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }


        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }


    @PutMapping("/place/addUser")
    public ResponseEntity addUser(
            @RequestHeader("token") String token,
            @RequestParam("placeId") Long placeId,
            @RequestParam("userId") Long userId
    ) {

        logger.info("%M token: {}, placeId: {}, userId: {}", token, placeId, userId);

        Object result;
        HttpStatus httpStatus;

        try {
            result = placeControllerService.addUserToPlace(token, placeId, userId);
            httpStatus = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            logger.error(e.toString());
            result = e;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }

}
