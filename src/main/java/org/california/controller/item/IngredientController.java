package org.california.controller.item;

import org.california.controller.service.IngredientControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class IngredientController {
    private IngredientControllerService controllerService;

    @Autowired
    public IngredientController(IngredientControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @GetMapping("/ingredients")
    public ResponseEntity search(
            @RequestParam(name = "ids", defaultValue = "") String idsString,
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "nameStart", defaultValue = "") String nameStart) {
        Object result;
        HttpStatus status;

        try {
            result = controllerService.search(idsString, name, nameStart);
            status = HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = e;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(status)
                .body(result);

    }


}
