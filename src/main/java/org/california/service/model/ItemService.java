package org.california.service.model;

import org.california.model.entity.builder.NutritionBuilder;
import org.california.model.entity.item.*;
import org.california.model.transfer.request.*;
import org.california.repository.item.ItemRepository;
import org.california.service.getter.GetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    private Item fromForm(ItemForm form) {
        Item item = new Item();

        return item;
    }


    private Ingredient ingredientFromForm(IngredientForm form) {
        Ingredient ingredient = getterService.ingredients.getByName(form.name);

        if(ingredient != null)
            return ingredient;

        ingredient = new Ingredient();
        ingredient.setName(form.name);
        return ingredient;
    }


    private Allergen allergenFromForm(AllergenForm form) {
        Allergen allergen;

        allergen = getterService.allergens.getOrCreateByName(form.name);

        if(allergen != null)
            return allergen;

        allergen = new Allergen();
        allergen.setName(form.name);

        return allergen;
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


    private Producer producerFromForm(ProducerForm form) {
        Producer producer;

        producer = getterService.producers.getByNameOrCreate(form.name);

        if(producer != null)
            return producer;

        producer = new Producer();
        producer.setName(form.name);

        return producer;
    }

}
