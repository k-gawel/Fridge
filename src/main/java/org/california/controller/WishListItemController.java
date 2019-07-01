package org.california.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.california.controller.service.WishListItemControllerService;
import org.california.model.transfer.request.ItemInstanceForm;
import org.california.model.transfer.request.WishListItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
public class WishListItemController {

    private final WishListItemControllerService controllerService;


    @Autowired
    public WishListItemController(WishListItemControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @PostMapping("wish_list_items")
    public ResponseEntity newItem(@RequestHeader("token") String token,
                                  @RequestBody WishListItemForm form) throws IOException
    {
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


    @PostMapping("wish_list_items/{id}/instances/{instanceId}")
    public ResponseEntity addInstance(@RequestHeader("token") String token,
                                      @PathVariable("instanceId") Long instanceId,
                                      @PathVariable("id") Long wishListItemId)
    {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.addInstanceToWishListItem(token, wishListItemId, instanceId);
            status = HttpStatus.OK;
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
