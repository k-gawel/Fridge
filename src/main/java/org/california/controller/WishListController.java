package org.california.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.california.controller.service.WishListControllerService;
import org.california.model.transfer.request.forms.WishListForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController("/wishlists")
@RequestMapping("/wishlists")
@CrossOrigin
public class WishListController extends BaseController {

    private final WishListControllerService controllerService;

    @Autowired
    public WishListController(WishListControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @PostMapping
    public ResponseEntity newWishList(@RequestHeader("token") String token,
                                      @Valid @RequestBody WishListForm wishListForm) {
        Object result;
        HttpStatus status;

        try {
            result = this.controllerService.newWishList(token, wishListForm);
            status = result == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }


        return ResponseEntity.status(status).body(result);
    }


    @GetMapping
    public ResponseEntity get(@RequestHeader("token") String token,
                              @RequestParam(name = "placeIds", defaultValue = "") String placesIds,
                              @RequestParam(name = "ids", defaultValue = "") String wishListIds,
                              @RequestParam(name = "active", defaultValue = "true") boolean active,
                              @RequestParam(name = "offset") Short offset,
                              @RequestParam(name = "limit") Short limit) {

        var result = this.controllerService.get(token, placesIds, wishListIds, active);
        var status = result == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@RequestHeader("token") String token,
                                 @PathVariable("id") Long wishListid) {

        var result = this.controllerService.archive(token, wishListid);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


}
