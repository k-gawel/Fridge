package org.california.controller;

import org.california.controller.service.RelatedItemsControllerService;
import org.california.model.entity.Account;
import org.california.model.transfer.request.queries.ItemGetQuery;
import org.california.service.serialization.annotations.ByToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/related_items")
@RequestMapping("/related_items")
@CrossOrigin
public class RelatedItemsController extends BaseController {

    private RelatedItemsControllerService controllerService;

    @Autowired
    public RelatedItemsController(RelatedItemsControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping
    public ResponseEntity get(@ByToken Account account, ItemGetQuery query) {

        var result = controllerService.get(account, query);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }


}
