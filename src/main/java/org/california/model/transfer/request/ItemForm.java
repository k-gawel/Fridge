package org.california.model.transfer.request;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;


public class ItemForm implements Serializable {

    public String name;
    public Long categoryId;
    public Long placeId;

    public long barcode;
    public String description;
    public String storage;

    public Map<String, Boolean> allergens;
    public Collection<String> ingredients;
    public NutritionForm nutrition;

    public String producer;
    public String capacity;

}
