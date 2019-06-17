package org.california.model.transfer.response;

import org.california.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EntityToDtoMapper {

    public PlaceDto placeToDto(Place place) {
        Collection<ContainerDto> containers = place.getContainers().stream().map(this::containerToDto).collect(Collectors.toList());;
        Collection<PlaceUserDto> placeUsers = placeUsersToDto(place.getAccounts(), place.getUnaactiveAccounts());

        return new PlaceDto.Builder()
                .setId(place)
                .setName(place.getName())
                .setAdminId(place.getAdmin())
                .setContainers(containers)
                .setUsers(placeUsers)
                .build();
    }

    public NamedEntityDto namedEntityToDto(BaseNamedEntity namedEntity) {
        return new NamedEntityDto.Builder()
                .setId(namedEntity)
                .setName(namedEntity.getName())
                .build();
    }

    public Collection<PlaceUserDto> placeUsersToDto(Collection<Account> active, Collection<Account> unactive) {
        Collection<PlaceUserDto> activeDto = active.stream()
                .map(this::accountToActivePlaceUserDto)
                .collect(Collectors.toList());;
        Collection<PlaceUserDto> unactiveDto = unactive.stream()
                .map(this::accountToUnactivePlaceUserDto)
                .collect(Collectors.toList());

        return Stream.concat(activeDto.stream(), unactiveDto.stream()).collect(Collectors.toList());
    }


    public PlaceUserDto accountToActivePlaceUserDto(Account account) {
        return toPlaceUser(account, true);
    }

    public PlaceUserDto accountToUnactivePlaceUserDto(Account account) {
        return toPlaceUser(account, false);
    }


    private PlaceUserDto toPlaceUser(Account account, boolean status) {
        return new PlaceUserDto.Builder()
                .setId(account)
                .setName(account)
                .setStatus(status)
                .build();
    }


    public ItemDto itemToDto(Item item) {
        return new ItemDto.Builder()
                .setId(item)
                .setName(item)
                .setBarcode(item)
                .setPlaceId(item.getPlace())
                .setCategoryId(item.getCategory())
                .setProducer(producerToDto(item.getProducent()))
                .setDescription(item)
                .setStorage(item)
                .setAllergens(item.getAllergens().stream().map(this::allergenToDto).collect(Collectors.toList()))
                .setIngredients(item.getIngredients().stream().map(this::ingredientToDto).collect(Collectors.toList()))
                .setNutrition(nutritionToDto(item.getNutrition()))
                .build();
    }


    public ProducerDto producerToDto(Producent producent) {
        return new ProducerDto.Builder()
                .setId(producent)
                .setName(producent.getName())
                .build();
    }


    public ItemInstanceDto itemInstanceToDto(ItemInstance itemInstance) {
        return new ItemInstanceDto.Builder()
                .setId(itemInstance)
                .setComment(itemInstance.getComment())
                .setExpireOn(itemInstance.getExpireOn())
                .setItemId(itemInstance.getItem())
                .setContainerId(itemInstance.getContainer())
                .setAddedById(itemInstance.getAddedBy())
                .setAddedOn(itemInstance.getAddedOn())
                .setOpenById(itemInstance.getOpenBy())
                .setOpenOn(itemInstance.getOpenOn())
                .setFrozenById(itemInstance.getFrozenBy())
                .setFrozenOn(itemInstance.getFrozenOn())
                .setDeletedById(itemInstance.getDeletedBy())
                .setDeletedOn(itemInstance.getDeletedOn())
                .build();
    }


    public ContainerDto containerToDto(Container container) {
        return new ContainerDto.Builder()
                .setId(container)
                .setName(container.getName())
                .setPlaceId(container.getPlace())
                .build();
    }


    public InstanceChangeDto instanceChangeToDto(InstanceChange instanceChange) {
        return new InstanceChangeDto.Builder()
                .setId(instanceChange)
                .setItemInstanceDto(this.itemInstanceToDto(instanceChange.getInstance()))
                .setAccountId(instanceChange.getAccount())
                .setChangeDate(instanceChange.getChangeDate())
                .setChangeOnInstance(instanceChange.getChangeType())
                .build();
    }


    public WishListItemDto wishListItemToDto(WishListItem WLI) {
        return new WishListItemDto.Builder()
                .setId(WLI)
                .setWishListId(WLI.getWishList())
                .setAuthorId(WLI.getAuthor())
                .setCreatedOn(WLI.getCreatedOn())
                .setAddedById(WLI.getAddedBy())
                .setAddedOn(WLI.getAddedOn())
                .setAddedInstanceId(WLI.getAddedInstance())
                .setCategoryId(WLI.getCategory())
                .setItemId(WLI.getItem())
                .setComment(WLI.getComment())
                .build();
    }


    public WishListDto wishListToDto(WishList WL) {
        return new WishListDto.Builder()
                .setId(WL.getId())
                .setName(WL.getName())
                .setDescription(WL.getDescription())
                .setPlaceId(WL.getPlace())
                .setItems(WL.getItems().stream().map(this::wishListItemToDto).collect(Collectors.toList()))
                .setStatus(WL.getStatus())
                .build();
    }


    public NutritionDto nutritionToDto(Nutrition nutrition) {
        return new NutritionDto.Builder()
                .setId(nutrition)
                .setEnergy(nutrition.getEnergy())
                .setFat(nutrition.getFat())
                .setSaturatedFat(nutrition.getSaturatedFat())
                .setCarbohydrate(nutrition.getCarbohydrate())
                .setSugar(nutrition.getSugar())
                .setProtein(nutrition.getProtein())
                .setSalt(nutrition.getSalt())
                .build();
    }


    public AllergenDto allergenToDto(Allergen allergen) {
        return new AllergenDto.Builder()
                .setId(allergen)
                .setName(allergen.getName())
                .doContains(allergen.isContains())
                .build();
    }


    public IngredientDto ingredientToDto(Ingredient ingredient) {
        return new IngredientDto.Builder()
                .setId(ingredient)
                .setName(ingredient.getName())
                .build();
    }

}
