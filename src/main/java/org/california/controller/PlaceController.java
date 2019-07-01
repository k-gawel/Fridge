package org.california.controller;

import org.california.controller.service.PlaceControllerService;
import org.california.model.transfer.request.PlaceForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class PlaceController {

    private PlaceControllerService placeControllerService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlaceController(PlaceControllerService placeControllerService) {
        this.placeControllerService = placeControllerService;
    }


    @PostMapping("/places")
    public ResponseEntity newPlace(
            @RequestHeader("token") String token,
            @Valid @RequestBody PlaceForm placeForm
    ) {
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


    @GetMapping("/places/{place_ids}")
    public ResponseEntity get(
            @RequestHeader("token") String token,
            @PathVariable(name = "ids") String placeIdsString
    ) {
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


    @PutMapping("/places/{place_id}/admin/{new_admin_id}")
    public ResponseEntity changeAdmin(
            @RequestHeader("token") String token,
            @PathVariable(name = "place_id") Long placeId,
            @PathVariable(name = "new_admin_id") Long newAdminId
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


    @DeleteMapping("/places/{place_id}/accounts/{account_id}")
    public ResponseEntity removeUser(
            @RequestHeader("token") String token,
            @PathVariable("place_id") Long placeId,
            @PathVariable("account_id") Long accountId
    ) {
        Object result;
        HttpStatus httpStatus;

        try {
            result = placeControllerService.removeUserFromPlace(token, placeId, accountId);
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


    @PostMapping("/place/{place_id}/acounts/{account_id}")
    public ResponseEntity addUser(
            @RequestHeader("token") String token,
            @PathVariable("place_id") Long placeId,
            @PathVariable("account_id") Long accountId
    ) {

        logger.info("%M token: {}, placeId: {}, userId: {}", token, placeId, accountId);

        Object result;
        HttpStatus httpStatus;

        try {
            result = placeControllerService.addUserToPlace(token, placeId, accountId);
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
