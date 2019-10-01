package org.california.controller.item;

import org.california.controller.BaseController;
import org.california.model.entity.item.Category;
import org.california.service.getter.GetterService;
import org.california.service.model.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController extends BaseController {

    private final GetterService getterService;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(GetterService getterService, CategoryService categoryService) {
        this.getterService = getterService;
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity getAllCategories() {

        var result = getterService.categories.getRootCategory();
        var status = HttpStatus.OK;

        return ResponseEntity.status(status).body(result);
    }


    @PostMapping
    public boolean createCategories() throws IOException {

        File rootFile = new ClassPathResource("/IS/Spożywczy").getFile();
        Category category;
        category = categoryService.createCategoriesOfDirectories(rootFile, null);

        return category.getId() != null;
    }


}