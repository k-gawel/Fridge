package org.california.controller;

import org.apache.commons.io.IOUtils;
import org.california.service.ItemBuilderService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class
AdminController {


    private final ItemBuilderService itemBuilderService;

    @Autowired
    public AdminController(ItemBuilderService itemBuilderService) {
        this.itemBuilderService = itemBuilderService;
    }

    @GetMapping("admin")
    public ModelAndView samplePage() {
        return new ModelAndView("index");
    }

    @GetMapping("admin/uploadProductsForm")
    public ModelAndView productsForm() {
        return new ModelAndView("upload");
    }

    @PostMapping("admin/uploadProducts")
    public ModelAndView submitProductsFile(@RequestParam("file") MultipartFile file) throws IOException {
        String content =  IOUtils.toString(file.getInputStream(), StandardCharsets.UTF_8);

        System.out.println(content);

        JSONArray jsonArray = new JSONArray(content);

        Map<JSONObject, Boolean> deserializeResult = itemBuilderService.deserialize(jsonArray);

        Collection<String> successful = deserializeResult.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .map(j -> j.getString("name"))
                .collect(Collectors.toSet());

        Collection<JSONObject> failed = deserializeResult.entrySet().stream()
                .filter(e -> !e.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        ModelAndView result = new ModelAndView("upload_status");
        result.addObject("content", content);
        result.addObject("succesful", successful);
        result.addObject("failed", failed);
        return result;
    }



}
