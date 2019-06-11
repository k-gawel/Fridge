package org.california.service.getter;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Category;
import org.california.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class CategoryGetter {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryGetter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category getByKey(Serializable key) {
        return categoryRepository.getByKey(key);
    }


    public Collection<Category> getByIds(Collection<Long> ids) {
        if(CollectionUtils.isEmpty(ids))
            return Collections.emptySet();

        return categoryRepository.getByIds(ids);
    }


    public Collection<Category> getFinalCategories() {
        return null;
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

}
