package org.california.controller;

import org.california.controller.service.RelatedItemsControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity get(@RequestHeader("token") String token,
                              @RequestParam(name = "category_id") Long categoryId,
                              @RequestParam(name = "place_ids", defaultValue = "") String placeIdsString,
                              @RequestParam(name = "params", defaultValue = "most_popular") String params) {

        var result = controllerService.get(token, placeIdsString, categoryId, params);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }


}
