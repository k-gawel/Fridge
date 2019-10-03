package org.california.controller;

import org.california.controller.service.IngredientControllerService;
import org.california.model.entity.item.Ingredient;
import org.california.service.serialization.annotations.ByIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("ingredients")
@CrossOrigin
public class IngredientController extends BaseController {

    private final IngredientControllerService controllerService;

    @Autowired
    public IngredientController(IngredientControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @GetMapping
    public ResponseEntity search(@ByIds(entity = Ingredient.class) Collection<Ingredient> ingredients,
                                 @RequestParam(name = "name", defaultValue = "") String name,
                                 @RequestParam(name = "nameStart", defaultValue = "") String nameStart) {

        var result = controllerService.search(ingredients, name, nameStart);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


}
