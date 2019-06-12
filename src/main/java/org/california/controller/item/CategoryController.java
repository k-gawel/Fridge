package org.california.controller.item;

import org.california.model.entity.Category;
import org.california.service.getter.GetterService;
import org.california.service.model.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
public class CategoryController {

    private GetterService getterService;
    private CategoryService categoryService;

    @Autowired
    public CategoryController(GetterService getterService, CategoryService categoryService) {
        this.getterService = getterService;
        this.categoryService = categoryService;
    }

    @GetMapping("/category/getAllCategories")
    public ResponseEntity getAllCategories() {

        Object result;
        HttpStatus httpStatus;

        try {
            result = getterService.categories.getByKey(2L);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = e;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }


    @GetMapping("/category/createCategories")
    public boolean createCategories() throws IOException {

        File rootFile = new ClassPathResource("/IS/Spożywczy").getFile();
        Category category;
        category = categoryService.createCategoriesOfDirectories(rootFile, null);

        return category.getId() != null;
    }


}