package org.california.controller;

import org.california.controller.service.ItemInstanceControllerService;
import org.california.model.transfer.request.forms.ItemInstanceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController("/item_instances")
@RequestMapping("/item_instances")
@CrossOrigin
public class ItemInstanceController extends BaseController {


    private final ItemInstanceControllerService controllerService;

    @Autowired
    public ItemInstanceController(ItemInstanceControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @PostMapping
    public ResponseEntity newItemInstance(@RequestHeader("token") final String token,
                                          @Valid @RequestBody final ItemInstanceForm itemInstanceForm
    ) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.addItemInstance(token, itemInstanceForm);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }


        return ResponseEntity.status(status).body(result);
    }


    @GetMapping
    public ResponseEntity get(@RequestHeader("token") String token,
                              @RequestParam(name = "ids", defaultValue = "") String ids,
                              @RequestParam(name = "items", defaultValue = "") String items,
                              @RequestParam(name = "places", defaultValue = "") String places,
                              @RequestParam(name = "containers", defaultValue = "") String containers,
                              @RequestParam(name = "owners", defaultValue = "") String owners,
                              @RequestParam(name = "deleted", defaultValue = "") String deleted,
                              @RequestParam(name = "open", defaultValue = "") String open,
                              @RequestParam(name = "frozen", defaultValue = "") String frozen,
                              @RequestParam(name = "limit", defaultValue = "0") int limit,
                              @RequestParam(name = "offset", defaultValue = "0") int offset) {
       Object result;
        HttpStatus status;

       try {
           result = controllerService.get(token, ids, places, containers, items, owners, deleted, open, frozen, limit, offset);
           status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
       } catch (Exception e) {
           result = result(e);
           status = status(e);
       }


        return ResponseEntity.status(status).body(result);
    }


    @PutMapping("/{instance_id}")
    public ResponseEntity update(@RequestHeader("token") String token,
                                 @PathVariable("instance_id") Long instanceId,
                                 @RequestParam(value = "frozeOrUnfroze", defaultValue = "false") boolean frozeOrUnfroze,
                                 @RequestParam(value = "open", defaultValue = "false") boolean open,
                                 @RequestParam(value = "delete", defaultValue = "false") boolean delete
    ) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.update(token, instanceId, frozeOrUnfroze, open, delete);
            status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            result = result(e);
            status = status(e);
        }


        return ResponseEntity.status(status).body(result);
    }

}