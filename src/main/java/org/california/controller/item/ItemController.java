package org.california.controller.item;


import org.california.controller.service.ItemControllerService;
import org.california.model.transfer.request.ItemForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ItemControllerService itemControllerService;


    @Autowired
    public ItemController(ItemControllerService itemControllerService) {
        this.itemControllerService = itemControllerService;
    }


    @GetMapping(value = "/item/search")
    public ResponseEntity searchItems(
            @RequestHeader(name = "token", defaultValue = "") String token,
            @RequestParam(name = "itemIds", defaultValue = "") String itemIdsString,
            @RequestParam(name = "placeIds", defaultValue = "") String placeIdsString,
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "barcode", defaultValue = "0") long barcode,
            @RequestParam(name = "category", defaultValue = "5") long categoryId
    ) {

        logger.info("Token: {}, itemIds: {}, placeIds: {}, name: {}, barcode: {}, category: {}",
                    token, itemIdsString, placeIdsString, name, barcode, categoryId);

        Object result;
        HttpStatus httpStatus;

        try {
            result = itemControllerService.searchItem(token, itemIdsString, placeIdsString, name, barcode, categoryId);
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


    @PostMapping(value = "/item/newItem")
    public ResponseEntity newItem(
            @RequestHeader("token") String token,
            @RequestBody ItemForm form
    ) {

        logger.info("Token: {}, form: {}", token, form);

        Object result;
        HttpStatus httpStatus;

        try {
            result = itemControllerService.addItem(token, form);
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


}
