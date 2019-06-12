package org.california.controller;

import org.california.controller.service.ItemInstanceControllerService;
import org.california.model.transfer.request.ItemInstanceForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class
ItemInstanceController {


    private ItemInstanceControllerService itemInstanceControllerService;

    @Autowired
    public ItemInstanceController(ItemInstanceControllerService itemInstanceControllerService) {

        this.itemInstanceControllerService = itemInstanceControllerService;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/itemInstance/new")
    public ResponseEntity newItemInstance(
            @RequestHeader("token") String token,
            @RequestBody ItemInstanceForm itemInstanceForm
    ) {

        logger.info("token: {}, form: {}", token, itemInstanceForm);

        Object result;
        HttpStatus httpStatus;

        try {
            result = itemInstanceControllerService.addItemInstance(token, itemInstanceForm);
            httpStatus = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            e.printStackTrace();
            result = e;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }


    @GetMapping("/itemInstance/get")
    public ResponseEntity get(
            @RequestHeader("token") String token,
            @RequestParam(name = "ids", defaultValue = "") String ids,
            @RequestParam(name = "items", defaultValue = "") String items,
            @RequestParam(name = "places", defaultValue = "") String places,
            @RequestParam(name = "containers", defaultValue = "") String containers,
            @RequestParam(name = "owners", defaultValue = "") String owners,
            @RequestParam(name = "deleted", defaultValue = "") String deleted,
            @RequestParam(name = "open", defaultValue = "") String open,
            @RequestParam(name = "frozen", defaultValue = "") String frozen,
            @RequestParam(name = "limit", defaultValue = "0") int limit
    ) {

       logger.info("token: {}, ids: {}, items {}, places: {}, containers: {}, owners: {}, deleted: {}, open: {}, frozen, {}, limit: {}",
                    token, ids, items, places, containers, owners, deleted, open, frozen, limit);

       Object result;
       HttpStatus httpStatus;

       try {
           result = itemInstanceControllerService.get(token, ids, places, containers, items, owners, deleted, open, frozen, limit);
           httpStatus = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
       } catch (Exception e) {
           e.printStackTrace();
           result = e;
           httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
       }

       return ResponseEntity
               .status(httpStatus)
               .body(result);
    }


    @PutMapping("/itemInstance/update/{instanceId}")
    public ResponseEntity update(
            @RequestHeader("token") String token,
            @PathVariable("instanceId") Long instanceId,
            @RequestParam(value = "frozeOrUnfroze", defaultValue = "false") boolean frozeOrUnfroze,
            @RequestParam(value = "open", defaultValue = "false") boolean open,
            @RequestParam(value = "delete", defaultValue = "false") boolean delete
    ) {

        logger.info("update() token: {}, instanceId: {}, frozeOrUnfroze {}, open {}, delete {}",
                            token, instanceId, frozeOrUnfroze, open, delete);

        Object result;
        HttpStatus httpStatus;

        try {
            result = itemInstanceControllerService.update(token, instanceId, frozeOrUnfroze, open, delete);
            httpStatus = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            e.printStackTrace();
            result = e;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return  ResponseEntity
                .status(httpStatus)
                .body(result);
    }

}