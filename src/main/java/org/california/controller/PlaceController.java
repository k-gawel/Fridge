package org.california.controller;

import org.california.controller.service.PlaceControllerService;
import org.california.model.entity.Account;
import org.california.model.transfer.request.forms.PlaceForm;
import org.california.service.serialization.annotations.ByToken;
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

        var result = controllerService.newPlace(token, placeForm);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }


    @GetMapping("/{place_ids}")
    public ResponseEntity get(@RequestHeader("token") String token,
                              @PathVariable("place_ids") String placeIdsString) {

        var result = controllerService.get(token, placeIdsString);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }


    @PutMapping("/{place_id}/admin/{new_admin_id}")
    public ResponseEntity changeAdmin(@RequestHeader("token") String token,
                                      @PathVariable(name = "place_id") Long placeId,
                                      @PathVariable(name = "new_admin_id") Long newAdminId) {

        var result = controllerService.changeAdmin(token, placeId, newAdminId);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }


    @DeleteMapping("/{place_id}/accounts/{account_id}")
    public ResponseEntity removeUser(@RequestHeader("token") String token,
                                     @PathVariable("place_id") Long placeId,
                                     @PathVariable("account_id") Long accountId) {

        var result = controllerService.removeUserFromPlace(token, placeId, accountId);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }


    @PostMapping("/{place_id}/acounts/{account_id}")
    public ResponseEntity addUser(@ByToken Account account,
                                  @PathVariable("place_id") Long placeId,
                                  @PathVariable("account_id") Long userId) {

        var result = controllerService.addUserToPlace(account, placeId, userId);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }

}
