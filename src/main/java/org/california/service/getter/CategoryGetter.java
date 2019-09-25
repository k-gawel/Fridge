package org.california.service.getter;

import org.california.model.entity.item.Category;
import org.california.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CategoryGetter extends BaseGetter<Category> {

    private final CategoryRepository categoryRepository;

    @Autowired
    CategoryGetter(CategoryRepository categoryRepository) {
        super(categoryRepository, Category.class);
        this.categoryRepository = categoryRepository;
    }


    public Collection<Category> getFinalCategories(Category category) {
        Collection<Category> result = new ArrayList<>();

        if(category.getId() == null)
            return result;

        try {
            if (category.getChildren().isEmpty()) {
                result.add(category);
            } else {
                for (Category child : category.getChildren()) {
                    result.addAll(getFinalCategories(child));
                }
            }
        } catch (Exception e) {
            return result;
        }

        return result;
    }


    public Category getRootCategory() {
        return categoryRepository.getByParent(null).stream().findFirst().orElseThrow(IllegalStateException::new);
    }

}
