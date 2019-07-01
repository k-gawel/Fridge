package org.california.service.model;

import org.california.model.entity.item.Category;
import org.california.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CategoryService {


    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category save(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Category addNewCategory(Category parent, String name) {
        Category category = new Category();
        category.setName(name);
        category.setParent(parent);

        if(parent != null)
            parent.getChildren().add(category);

        return save(category);
    }


    public Category createCategoriesOfDirectories(File rootFile, Category parentCategory) {

        if(!rootFile.isDirectory())
            return null;

        Category category = addNewCategory(parentCategory, rootFile.getName());

        for(File file : rootFile.listFiles())
            if(file.isDirectory()) {
                Category child = createCategoriesOfDirectories(file, category);
                if (child != null)
                    category.getChildren().add(child);
            }

        return category;
    }

}
