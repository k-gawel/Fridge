package org.california.controller;

import org.california.controller.service.AllergenControllerService;
import org.california.model.entity.item.Allergen;
import org.california.service.serialization.annotations.ByIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/allergens")
@CrossOrigin
public class AllergenController extends BaseController {

    private final AllergenControllerService controllerService;

    @Autowired
    public AllergenController(AllergenControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @GetMapping
    public ResponseEntity search(@ByIds(entity = Allergen.class) Collection<Allergen> allergens,
                                 @RequestParam(name = "name", defaultValue = "") String name,
                                 @RequestParam(name = "nameStart", defaultValue = "") String nameStart) {

        var result = controllerService.search(allergens, name, nameStart);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }

}
