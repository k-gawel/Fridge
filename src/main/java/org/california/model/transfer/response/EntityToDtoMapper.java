package org.california.model.transfer.response;

import org.california.model.entity.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class EntityToDtoMapper {

    public PlaceDto placeToDto(Place place) {
        if(place == null)
            throw new NullPointerException("place is null");

        PlaceDto.Builder builder;
        builder = new PlaceDto.Builder();

        builder.setId(place.getId());
        builder.setName(place.getName());
        builder.setAdminId(place.getAdmin().getId());

        Collection<ContainerDto> containerDtos = place.getContainers().stream()
                .map(this::containerToDto).collect(Collectors.toList());
        builder.setContainers(containerDtos);

        Collection<PlaceUserDto> placeUserDtos = new ArrayList<>();
        place.getAccounts().stream().map(this::accountToActivePlaceUserDto).forEachOrdered(placeUserDtos::add);
        place.getUnaactiveAccounts().stream().map(this::accountToUnactivePlaceUserDto).forEach(placeUserDtos::add);
        builder.setUsers(placeUserDtos);

        return builder.build();

    }


    public NamedEntityDto namedEntityToDto(BaseNamedEntity namedEntity) {
        if(namedEntity == null)
            return null;

        NamedEntityDto.Builder builder = new NamedEntityDto.Builder();

        builder.setId(namedEntity.getId())
                .setName(namedEntity.getName());

        return builder.build();
    }


    public PlaceUserDto accountToActivePlaceUserDto(Account account) {
        return toPlaceUser(account, true);
    }


    public PlaceUserDto accountToUnactivePlaceUserDto(Account account) {
        return toPlaceUser(account, false);
    }


    private PlaceUserDto toPlaceUser(Account account, boolean status) {
        if(account == null)
            throw new NullPointerException("account is null");

        PlaceUserDto.Builder builder;
        builder = new PlaceUserDto.Builder();

        builder.setId(account.getId());
        builder.setName(account.getName());
        builder.setStatus(status);

        return builder.build();
    }


    public ItemDto itemToDto(Item item) {
        if(item == null)
            throw new NullPointerException("item is null");

        ItemDto.Builder builder;

        builder = new ItemDto.Builder();
        builder.setId(item.getId());
        builder.setName(item.getName());
        builder.setBarcode(item.getBarcode());

        builder.setPlaceId(item.getPlace() != null ? item.getPlace().getId() : null);
        builder.setCategoryId(item.getCategory().getId());
        builder.setProducer(producerToDto(item.getProducent()));

        builder.setDescription(item.getDescription());
        builder.setStorage(item.getStorage());

        builder.setAllergens(item.getAllergens().stream().map(this::allergenToDto).collect(Collectors.toList()));
        builder.setIngredients(item.getIngredients().stream().map(this::ingredientToDto).collect(Collectors.toList()));
        builder.setNutrition(nutritionToDto(item.getNutrition()));

        return builder.build();
    }


    public ProducerDto producerToDto(Producent producent) {
        if(producent == null)
            return null;

        ProducerDto.Builder builder = new ProducerDto.Builder();

        builder.setId(producent.getId());
        builder.setName(producent.getName());

        return builder.build();
    }


    public ItemInstanceDto itemInstanceToDto(ItemInstance itemInstance) {
        if (itemInstance == null)
            throw new NullPointerException("item instance is null");

        ItemInstanceDto.Builder builder;

        builder = new ItemInstanceDto.Builder();

        builder.setId(itemInstance.getId());
        builder.setComment(itemInstance.getComment());
        builder.setExpireOn(toLocalDate(itemInstance.getExpireOn()));

        builder.setItemId(itemInstance.getItem().getId());
        builder.setContainerId(itemInstance.getContainer().getId());

        builder.setAddedById(itemInstance.getAddedBy().getId());
        builder.setAddedOn(toLocalDate(itemInstance.getAddedOn()));

        if (itemInstance.getOpenBy() != null && itemInstance.getOpenOn() != null) {
            builder.setOpenById(itemInstance.getOpenBy().getId());
            builder.setOpenOn(toLocalDate(itemInstance.getOpenOn()));
        }

        if (itemInstance.getFrozenBy() != null && itemInstance.getFrozenOn() != null) {
            builder.setFrozenById(itemInstance.getFrozenBy().getId());
            builder.setFrozenOn(toLocalDate(itemInstance.getFrozenOn()));
        }

        if (itemInstance.getDeletedBy() != null && itemInstance.getDeletedOn() != null) {
            builder.setDeletedById(itemInstance.getDeletedBy().getId());
            builder.setDeletedOn(toLocalDate(itemInstance.getDeletedOn()));
        }

        return builder.build();
    }


    private LocalDate toLocalDate(Date date) {
        if(date == null)
            return null;

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    public ContainerDto containerToDto(Container container) {
        if(container == null)
            throw new NullPointerException();

        ContainerDto.Builder builder;

        builder = new ContainerDto.Builder();
        builder.setId(container.getId());
        builder.setPlaceId(container.getPlace().getId());
        builder.setName(container.getName());

        return builder.build();
    }


    public InstanceChangeDto instanceChangeToDto(InstanceChange instanceChange) {
        if(instanceChange == null)
            throw new IllegalStateException("instance changeType is null");

        InstanceChangeDto.Builder builder = new InstanceChangeDto.Builder();

        builder.setId(instanceChange.getId());
        builder.setAccountId(instanceChange.getAccount().getId());
        builder.setInstance(itemInstanceToDto(instanceChange.getInstance()));
        builder.setChangeDate(instanceChange.getChangeDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        builder.setChange(instanceChange.getChangeType());

        return builder.build();
    }


    public WishListItemDto wishListItemToDto(WishListItem wishListItem) {
        if(wishListItem == null)
            throw new IllegalStateException("wish list item is null");

        WishListItemDto.Builder builder = new WishListItemDto.Builder();

        builder.setId(wishListItem.getId());
        builder.setCreatedOn(wishListItem.getCreatedOn());
        builder.setAuthorId(wishListItem.getAuthor().getId());
        builder.setWishListId(wishListItem.getWishList().getId());

        builder.setItemId(wishListItem.getItem() != null ? wishListItem.getItem().getId() : null);
        builder.setCategoryId(wishListItem.getCategory() != null ? wishListItem.getCategory().getId() : null);
        builder.setComment(wishListItem.getComment());

        builder.setAddedInstanceId(wishListItem.getAddedInstance() != null ? wishListItem.getAddedInstance().getId() : null);
        builder.setAddedById(wishListItem.getAddedBy() != null ? wishListItem.getAddedBy().getId() : null);
        builder.setAddedOn(wishListItem.getAddedOn());

        return builder.build();
    }


    public WishListDto wishListToDto(WishList wishList) {
        if(wishList == null)
            throw new IllegalStateException("wish list is null");

        WishListDto.Builder builder = new WishListDto.Builder();

        builder.setId(wishList.getId());
        builder.setDescription(wishList.getDescription());
        builder.setItems( wishList.getItems().stream().map(this::wishListItemToDto).collect(Collectors.toList()) );
        builder.setName(wishList.getName());
        builder.setPlaceId(wishList.getPlace().getId());
        builder.setStatus(wishList.getStatus());

        return builder.build();
    }


    public NutritionDto nutritionToDto(Nutrition nutrition) {
        if(nutrition == null)
            return null;

        NutritionDto.Builder builder = new NutritionDto.Builder();

        builder.setId(nutrition.getId())
                .setCarbohydrate(nutrition.getCarbohydrate())
                .setEnergy(nutrition.getEnergy())
                .setFat(nutrition.getFat())
                .setProtein(nutrition.getProtein())
                .setSalt(nutrition.getSalt())
                .setSaturatedFat(nutrition.getSaturatedFat())
                .setSugar(nutrition.getSugar());

        return builder.build();
    }


    public AllergenDto allergenToDto(Allergen allergen) {
        if (allergen == null)
            return null;

        AllergenDto.Builder builder = new AllergenDto.Builder();

        builder.setId(allergen.getId())
                .setName(allergen.getName());

        return builder.build();
    }


    public IngredientDto ingredientToDto(Ingredient ingredient) {
        if(ingredient == null)
            return null;

        IngredientDto.Builder builder = new IngredientDto.Builder();

        builder.setId(ingredient.getId())
                .setName(ingredient.getName());

        return builder.build();
    }

}
