package org.california.controller;

import org.california.controller.service.RelatedItemsControllerService;
import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.service.serialization.annotations.ById;
import org.california.service.serialization.annotations.ByIds;
import org.california.service.serialization.annotations.ByToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
    public ResponseEntity get(@ByToken Account account,
                              @ById Category category,
                              @ByIds(entity = Place.class) Collection<Place> places,
                              @RequestParam(name = "params", defaultValue = "most_popular") String params) {

        var result = controllerService.get(account, places, category, params);
        var status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(result);
    }


}
