package org.california.service.model;

import org.california.model.entity.builder.ItemBuilder;
import org.california.model.entity.builder.NutritionBuilder;
import org.california.model.entity.item.*;
import org.california.model.transfer.request.forms.ItemForm;
import org.california.model.transfer.request.forms.NutritionForm;
import org.california.repository.item.AllergenRepository;
import org.california.repository.item.ItemRepository;
import org.california.service.getter.GetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final AllergenRepository allergenRepository;
    private final GetterService getterService;

    @Autowired
    public ItemService(ItemRepository itemRepository, GetterService getterService, AllergenRepository allergenRepository) {
        this.itemRepository = itemRepository;
        this.getterService = getterService;
        this.allergenRepository = allergenRepository;
    }


    public Item create(ItemForm form) {
        Item item = fromForm(form);
        return itemRepository.save(item);
    }


    private Item fromForm(ItemForm form) {
        return new ItemBuilder()
                .setName(form.name)
                .setBarcode(form.barcode)
                .setPlace(form.place)
                .setProducer(producerFromForm(form.producer))
                .setCategory(form.category)
                .setDescription(form.description)
                .setStorage(form.storage)
                .setNutrition(nutritionFromForm(form.nutrition))
                .setIngredients(ingredientsFromForm(form.ingredients))
                .setAllergens(allergensFromForm(form.allergens))
                .setCapacity(new Capacity(form.capacity))
                .build();
    }


    private List<Ingredient> ingredientsFromForm(Collection<String> list) {
        return list.stream().map(this::getIngredient).collect(Collectors.toList());
    }


    private Ingredient getIngredient(String name) {
        return getterService.ingredients
                .getByName(name)
                .orElseGet(Ingredient::new);
    }


    private Map<Allergen, Boolean> allergensFromForm(Map<String, Boolean> map) {
        Map<Allergen, Boolean> result = new HashMap<>();

        for (Map.Entry<String, Boolean> entry : map.entrySet())
            result.put(getAllergen(entry.getKey()), entry.getValue());

        return result;
    }


    private Allergen getAllergen(String name) {
        return getterService.allergens.getByName(name).orElseGet(() -> {
            Allergen newAllergen = new Allergen();
            newAllergen.setName(name);
            return allergenRepository.save(newAllergen);
        });
    }


    private Nutrition nutritionFromForm(NutritionForm form) {
        return new NutritionBuilder()
                .setEnergy(form.energy_kj, form.energy_kj)
                .setFat(form.fat, form.saturated_fat)
                .setCarbohydrates(form.carbohydrate, form.sugar)
                .setProteins(form.protein)
                .setSalt(form.salt)
                .build();
    }


    private Producer producerFromForm(String name) {
        return getterService.producers.getByName(name).orElseGet(() -> {
            Producer producer = new Producer();
            producer.setName(name);
            return producer;
        });
    }

}
