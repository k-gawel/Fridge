package org.california.controller;

import org.california.controller.service.WishListControllerService;
import org.california.model.entity.Account;
import org.california.model.transfer.request.forms.WishListForm;
import org.california.model.transfer.request.queries.WishListGetQuery;
import org.california.service.serialization.annotations.ByToken;
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
    public ResponseEntity newWishList(@ByToken Account account,
                                      @Valid @RequestBody WishListForm wishListForm) {

        var result = this.controllerService.newWishList(account, wishListForm);
        var status = result == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


    @GetMapping
    public ResponseEntity get(@ByToken Account account,
                              @RequestBody WishListGetQuery query) {

        var result = this.controllerService.get(account, query);
        var status = result == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@ByToken Account account,
                                 @PathVariable("id") Long wishListid) {

        var result = this.controllerService.archive(account, wishListid);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


}
