package org.california.controller;


import org.california.controller.service.ItemControllerService;
import org.california.model.transfer.request.forms.ItemForm;
import org.california.model.transfer.request.queries.ItemGetQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
@CrossOrigin
public class ItemController extends BaseController {

    private final ItemControllerService itemControllerService;

    @Autowired
    public ItemController(ItemControllerService itemControllerService) {
        this.itemControllerService = itemControllerService;
    }


    @GetMapping
    public ResponseEntity searchItems(@RequestHeader(name = "token", defaultValue = "") String token,
                                      @RequestBody ItemGetQuery query) {

        var result = itemControllerService.searchItem(token, query);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }


    @PostMapping
    public ResponseEntity newItem(@RequestHeader("token") String token,
                                  @Valid @RequestBody ItemForm form) {
        Object result;
        HttpStatus status;

        try {
            result = itemControllerService.addItem(token, form);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }

        return ResponseEntity.status(status).body(result);
    }


}


