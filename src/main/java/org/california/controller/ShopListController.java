package org.california.controller;

import org.california.controller.service.ShopListControllerService;
import org.california.model.transfer.request.forms.ShopListForm;
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


    @PostMapping
    public ResponseEntity createShopList(@RequestHeader("token") String token,
                                         @RequestBody ShopListForm form) {

        var result = controllerService.createShopList(token, form);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


    @DeleteMapping("{shop_list_id}")
    public ResponseEntity archiveShopList(@RequestHeader("token") String token,
                                          @PathVariable("shop_list_id") Long shopListId) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.archiveShopList(token, shopListId);
            status = (boolean) result ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }

        return ResponseEntity.status(status).body(result);
    }


    @PostMapping("{shop_list_id}/instances/{instance_id}")
    public ResponseEntity addItemInstanceToShopList(@RequestHeader("token") String token,
                                                    @PathVariable("shop_list_id") Long shopListId,
                                                    @PathVariable("instance_id") Long instanceId) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.addItemInstanceToShopList(token, shopListId, instanceId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }


        return ResponseEntity.status(status).body(result);
    }



    @DeleteMapping("{shop_list_id}/instances/{instance_id}")
    public ResponseEntity deleteItemInstanceFromShopList(@RequestHeader("token") String token,
                                                         @PathVariable("shop_list_id") Long shopListId,
                                                         @PathVariable("instance_id") Long instanceId) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.deleteInstanceFromShopList(token, shopListId, instanceId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }

        return ResponseEntity.status(status).body(result);
    }


}
