package org.california.service.model;

import org.california.model.entity.*;
import org.california.model.transfer.request.*;
import org.california.repository.item.ItemRepository;
import org.california.service.getter.GetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ItemService {

    private ItemRepository itemRepository;
    private GetterService getterService;

    @Autowired
    public ItemService(ItemRepository itemRepository, GetterService getterService, ProducentService producentService) {
        this.itemRepository = itemRepository;
        this.getterService = getterService;
    }


    public Item create(ItemForm form) {
        Item item = fromForm(form);
        return itemRepository.save(item);
    }


    public boolean setPublic(Item item) {
        item.setPlace(null);
        return itemRepository.save(item) != null;
    }


    private Item fromForm(ItemForm form) {
        Item item = new Item();

        item.setName(form.getName());
        item.setCategory(getterService.categories.getByKey(form.getCategoryId()));
        item.setBarcode(form.getBarcode());
        item.setNetWeight(form.getNetWeight());

        item.setPlace(getterService.places.getByKey(form.getPlaceId()));
        item.setDescription(form.getDescription());
        item.setStorage(form.getStorage());
        item.setProducent(producerFromForm(form.getProducer()));

        item.setAllergens(form.getAllergens().stream().map(this::allergenFromForm).collect(Collectors.toList()));
        item.setIngredients(form.getIngredients().stream().map(this::ingredientFromForm).collect(Collectors.toList()));
        item.setNutrition(nutritionFromForm(form.getNutrition()));

        return item;
    }


    private Ingredient ingredientFromForm(IngredientForm form) {
        Ingredient ingredient = getterService.ingredients.getByName(form.getName());

        if(ingredient != null)
            return ingredient;

        ingredient = new Ingredient();
        ingredient.setName(form.getName());
        return ingredient;
    }


    private Allergen allergenFromForm(AllergenForm form) {
        Allergen allergen;

        allergen = getterService.allergens.getByName(form.getName());

        if(allergen != null)
            return allergen;

        allergen = new Allergen();
        allergen.setName(form.getName());

        return allergen;
    }


    private Nutrition nutritionFromForm(NutritionForm form) {
        if(form == null)
            return null;

        Nutrition nutrition = new Nutrition();

        nutrition.setCarbohydrate(form.getCarbohydrate());
        nutrition.setEnergy(form.getEnergy());
        nutrition.setFat(form.getFat());
        nutrition.setProtein(form.getProtein());
        nutrition.setSalt(form.getSalt());
        nutrition.setSaturatedFat(form.getSaturatedFat());
        nutrition.setSugar(form.getSugar());

        return nutrition;
    }


    private Producent producerFromForm(ProducerForm form) {
        if(form == null)
            return null;

        Producent producer;

        producer = getterService.producers.getByName(form.getName());

        if(producer != null)
            return producer;

        producer = new Producent();
        producer.setName(form.getName());

        return producer;
    }

}
