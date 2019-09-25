package org.california.controller;

import org.california.controller.service.PlaceControllerService;
import org.california.model.transfer.request.forms.PlaceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/places")
@RequestMapping("/places")
@CrossOrigin
public class PlaceController extends BaseController {

    private PlaceControllerService controllerService;


    @Autowired
    public PlaceController(PlaceControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @PostMapping
    public ResponseEntity newPlace(@RequestHeader("token") String token,
                                   @Valid @RequestBody PlaceForm placeForm
    ) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.newPlace(token, placeForm);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }

        return ResponseEntity.status(status).body(result);
    }


    @GetMapping("/{place_ids}")
    public ResponseEntity get(@RequestHeader("token") String token,
                              @PathVariable("place_ids") String placeIdsString
    ) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.get(token, placeIdsString);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }


        return ResponseEntity.status(status).body(result);
    }


    @PutMapping("/{place_id}/admin/{new_admin_id}")
    public ResponseEntity changeAdmin(@RequestHeader("token") String token,
                                      @PathVariable(name = "place_id") Long placeId,
                                      @PathVariable(name = "new_admin_id") Long newAdminId
    ) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.changeAdmin(token, placeId, newAdminId);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }


        return ResponseEntity.status(status).body(result);
    }


    @DeleteMapping("/{place_id}/accounts/{account_id}")
    public ResponseEntity removeUser(@RequestHeader("token") String token,
                                     @PathVariable("place_id") Long placeId,
                                     @PathVariable("account_id") Long accountId
    ) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.removeUserFromPlace(token, placeId, accountId);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }


        return ResponseEntity.status(status).body(result);
    }


    @PostMapping("/{place_id}/acounts/{account_id}")
    public ResponseEntity addUser(@RequestHeader("token") String token,
                                  @PathVariable("place_id") Long placeId,
                                  @PathVariable("account_id") Long accountId
    ) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.addUserToPlace(token, placeId, accountId);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }


        return ResponseEntity.status(status).body(result);
    }

}
