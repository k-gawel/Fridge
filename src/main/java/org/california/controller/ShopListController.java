package org.california.controller;

import org.california.controller.service.shoplist.ShopListControllerService;
import org.california.model.entity.Account;
import org.california.model.transfer.request.forms.ShopListForm;
import org.california.model.transfer.request.queries.ShopListGetQuery;
import org.california.service.serialization.annotations.ByToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/shoplists/")
@RequestMapping("/shoplists/")
@CrossOrigin
public class ShopListController extends BaseController {

    private final ShopListControllerService controllerService;

    @Autowired
    public ShopListController(ShopListControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @GetMapping
    public ResponseEntity get(@ByToken Account account,
                              ShopListGetQuery query) {

        var result = controllerService.get(account, query);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }

    @PostMapping
    public ResponseEntity createShopList(@ByToken Account account,
                                         @RequestBody ShopListForm form) {

        var result = controllerService.createShopList(account, form);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


    @DeleteMapping("{shop_list_id}")
    public ResponseEntity archiveShopList(@ByToken Account account,
                                          @PathVariable("shop_list_id") Long shopListId) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.archiveShopList(account, shopListId);
            status = (boolean) result ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }

        return ResponseEntity.status(status).body(result);
    }


    @PostMapping("{shop_list_id}/instances/{instance_id}")
    public ResponseEntity addItemInstanceToShopList(@ByToken Account account,
                                                    @PathVariable("shop_list_id") Long shopListId,
                                                    @PathVariable("instance_id") Long instanceId) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.addItemInstanceToShopList(account, shopListId, instanceId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }


        return ResponseEntity.status(status).body(result);
    }



    @DeleteMapping("{shop_list_id}/instances/{instance_id}")
    public ResponseEntity deleteItemInstanceFromShopList(@ByToken Account account,
                                                         @PathVariable("shop_list_id") Long shopListId,
                                                         @PathVariable("instance_id") Long instanceId) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.deleteInstanceFromShopList(account, shopListId, instanceId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }

        return ResponseEntity.status(status).body(result);
    }


}
