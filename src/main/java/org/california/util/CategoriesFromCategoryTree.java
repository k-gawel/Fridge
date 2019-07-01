package org.california.util;


import org.california.model.entity.item.Category;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class CategoriesFromCategoryTree {


    public static void main(String[] args) {

        File rootFile = new File("IS/Spożywczy");;
    }


    public static void iterateThroughCategories(Category rootCategory, int tabLength) {

        String singleTab = " ";
        String tab = IntStream.range(0, tabLength).mapToObj(i -> singleTab).collect(Collectors.joining(""));

        System.out.println(tab + rootCategory.getName());

        tabLength++;

        for(Category category : rootCategory.getChildren())
                iterateThroughCategories(category, tabLength);

    }




}
