package org.california.controller;


import org.california.controller.service.WishListItemControllerService;
import org.california.model.entity.Account;
import org.california.model.transfer.request.forms.WishListItemForm;
import org.california.service.serialization.annotations.ByToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/wish_list_items")
@RequestMapping("/wish_list_items")
@CrossOrigin
public class WishListItemController extends BaseController {

    private final WishListItemControllerService controllerService;

    @Autowired
    public WishListItemController(WishListItemControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @PostMapping
    public ResponseEntity newItem(@ByToken Account account,
                                  @RequestBody @Valid WishListItemForm form)
    {
        var result = controllerService.newWishListItem(account, form);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }


    @PostMapping("/{id}/instances/{instanceId}")
    public ResponseEntity addInstance(@ByToken Account account,
                                      @PathVariable("instanceId") Long instanceId,
                                      @PathVariable("id") Long wishListItemId) {

        var result = controllerService.addInstanceToWishListItem(account, wishListItemId, instanceId);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }

}
