package org.california.controller;

import org.california.controller.service.ProducerControllerService;
import org.california.model.entity.item.Producer;
import org.california.service.serialization.annotations.ByIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
    public ResponseEntity search(@ByIds(entity = Producer.class)Collection<Producer> producers,
                                 @RequestParam(name = "name", defaultValue = "") String name,
                                 @RequestParam(name = "nameStart", defaultValue = "") String nameStart) {

        var result = controllerService.search(producers, name, nameStart);
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


}
