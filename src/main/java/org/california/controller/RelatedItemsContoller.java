package org.california.controller;

import org.california.controller.service.RelatedItemsControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/related_items")
@CrossOrigin
public class RelatedItemsContoller {

    private RelatedItemsControllerService relatedItemsControllerService;

    @Autowired
    public RelatedItemsContoller(RelatedItemsControllerService relatedItemsControllerService) {
        this.relatedItemsControllerService = relatedItemsControllerService;
    }

    @GetMapping
    public ResponseEntity get(
            @RequestHeader("token") String token,
            @RequestParam(name = "category_id", defaultValue = "0") Long categoryId,
            @RequestParam(name = "place_ids", defaultValue = "") String placeIdsString,
            @RequestParam(name = "params", defaultValue = "most_popular") String params
    ) {
        Object result;
        HttpStatus httpStatus;

        try {
            result = relatedItemsControllerService.get(token, placeIdsString, categoryId, params);
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
