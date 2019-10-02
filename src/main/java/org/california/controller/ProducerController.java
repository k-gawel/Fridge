package org.california.controller;

import org.california.controller.service.ProducerControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producers")
@CrossOrigin
public class ProducerController extends BaseController {

    private ProducerControllerService controllerService;

    @Autowired
    public ProducerController(ProducerControllerService controllerService) {
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
