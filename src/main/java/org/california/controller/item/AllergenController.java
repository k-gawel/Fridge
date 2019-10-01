package org.california.controller.item;

import org.california.controller.BaseController;
import org.california.controller.service.AllergenControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity search(@RequestParam(name = "ids", defaultValue = "") String idsString,
                                 @RequestParam(name = "name", defaultValue = "") String name,
                                 @RequestParam(name = "nameStart", defaultValue = "") String nameStart) {

        var result = controllerService.search(idsString, name, nameStart);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }

}
