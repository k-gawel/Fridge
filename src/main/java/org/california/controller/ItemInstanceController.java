package org.california.controller;

import org.california.controller.service.ItemInstanceControllerService;
import org.california.model.entity.Account;
import org.california.model.transfer.request.forms.ItemInstanceForm;
import org.california.model.transfer.request.queries.ItemInstanceGetQuery;
import org.california.service.serialization.annotations.ByToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController("/itemInstances")
@RequestMapping("/itemInstances")
@CrossOrigin
public class ItemInstanceController extends BaseController {


    private final ItemInstanceControllerService controllerService;

    @Autowired
    public ItemInstanceController(ItemInstanceControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @PostMapping
    public ResponseEntity newItemInstance(@ByToken Account account,
                                          @Valid @RequestBody ItemInstanceForm itemInstanceForm
    ) {

        var result = controllerService.addItemInstance(account, itemInstanceForm);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }


    @GetMapping
    public ResponseEntity get(@ByToken Account account,
                              @RequestBody ItemInstanceGetQuery query) {

       var result = controllerService.get(account, query);
       var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }


    @PutMapping("/{instance_id}")
    public ResponseEntity update(@ByToken Account account,
                                 @PathVariable("instance_id") Long instanceId,
                                 @RequestParam(value = "frozeOrUnfroze", defaultValue = "false") boolean frozeOrUnfroze,
                                 @RequestParam(value = "open", defaultValue = "false") boolean open,
                                 @RequestParam(value = "delete", defaultValue = "false") boolean delete) {

        var result = controllerService.update(account, instanceId, frozeOrUnfroze, open, delete);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }

}