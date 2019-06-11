package org.california.controller;


import org.california.controller.service.WishListItemControllerService;
import org.california.model.transfer.request.ItemInstanceForm;
import org.california.model.transfer.request.WishListItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class WishListItemController {

    private WishListItemControllerService controllerService;

    @Autowired
    public WishListItemController(WishListItemControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @PostMapping("wishListItem/new")
    public ResponseEntity newItem(
            @RequestHeader("token") String token,
            @RequestBody WishListItemForm form) {

        Object result;
        HttpStatus status;

        try {
            result = controllerService.newWishListItem(token, form);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            e.printStackTrace();
            result = e;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(status)
                .body(result);

    }


    @PostMapping("wishListItem/{id}/addInstance")
    public ResponseEntity addInstance(@RequestHeader("token") String token,
                                      @RequestBody ItemInstanceForm instanceForm,
                                      @PathVariable("id") Long wishListItemId) {

        Object result;
        HttpStatus status;

        try {
            result = controllerService.addInstanceToWishListItem(token, wishListItemId, instanceForm);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            e.printStackTrace();
            result = e;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(status)
                .body(result);
    }

}
