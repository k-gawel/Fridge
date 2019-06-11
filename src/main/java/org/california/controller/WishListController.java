package org.california.controller;

import org.california.controller.service.WishListControllerService;
import org.california.model.transfer.request.WishListForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class WishListController {

    private WishListControllerService wishListControllerService;

    @Autowired
    public WishListController(WishListControllerService wishListControllerService) {
        this.wishListControllerService = wishListControllerService;
    }

    @PostMapping("/wishlist/newWishlist")
    public ResponseEntity newWishList(
            @RequestHeader("token") String token,
            @RequestBody WishListForm wishListForm
    ) {
        Object result;
        HttpStatus httpStatus;

        try {
            result = this.wishListControllerService.newWishList(token, wishListForm);
            httpStatus = result == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = e;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }


    @GetMapping("/wishlist/get")
    public ResponseEntity get(
            @RequestHeader("token") String token,
            @RequestParam(name = "placeIds", defaultValue = "") String placesIds,
            @RequestParam(name = "ids", defaultValue = "") String wishListIds,
            @RequestParam(name = "active", defaultValue = "true") boolean active
    ) {
        Object result;
        HttpStatus httpStatus;

        try {
            result = this.wishListControllerService.get(token, placesIds, wishListIds, active);
            httpStatus = result == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
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
