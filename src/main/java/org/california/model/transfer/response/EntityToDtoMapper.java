package org.california.model.transfer.response;

import org.california.model.entity.*;
import org.california.model.entity.item.*;
import org.california.service.getter.GetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EntityToDtoMapper {

    private final GetterService getter;

    @Autowired
    public EntityToDtoMapper(GetterService getter) {
        this.getter = getter;
    }

    public NamedEntityDto toDto(BaseNamedEntity namedEntity) {
        return new NamedEntityDto.Builder()
                .setId(namedEntity)
                .setName(namedEntity.getName())
                .build();
    }


    public PlaceDto toDto(Place place) {
        Collection<ContainerDto> containers = place.getContainers().stream().map(this::toDto).collect(Collectors.toList());;
        Collection<PlaceUserDto> placeUsers = placeUsersToDto(place);

        return new PlaceDto.Builder()
                .setId(place)
                .setName(place.getName())
                .setAdminId(place.getAdmin())
                .setContainers(containers)
                .setUsers(placeUsers)
                .build();
    }

    private Collection<PlaceUserDto> placeUsersToDto(Place p) {
        Collection<PlaceUserDto> activeDto = p.getAccounts().stream()
                .map(a -> this.placeUserToDto(a, p, true))
                .collect(Collectors.toList());;
        Collection<PlaceUserDto> unactiveDto = p.getUnaactiveAccounts().stream()
                .map(a -> this.placeUserToDto(a, p, false))
                .collect(Collectors.toList());

        return Stream.concat(activeDto.stream(), unactiveDto.stream()).collect(Collectors.toList());
    }


    public ContainerDto toDto(Container container) {
        return new ContainerDto.Builder()
                .setId(container)
                .setName(container.getName())
                .setPlaceId(container.getPlace())
                .build();
    }

    private PlaceUserDto placeUserToDto(Account account, Place place, boolean status) {
        return new PlaceUserDto.Builder()
                .setId(account)
                .setName(account)
                .setStatus(status)
                .setStats(getter.placeUserStats.getStats(account, place))
                .build();
    }


    public ItemInstanceDto toDto(ItemInstance itemInstance) {
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


    public InstanceChangeDto toDto(InstanceChange instanceChange) {
        return new InstanceChangeDto.Builder()
                .setId(instanceChange)
                .setItemInstanceDto(this.toDto(instanceChange.getInstance()))
                .setAccountId(instanceChange.getAccount())
                .setChangeDate(instanceChange.getChangeDate())
                .setChangeOnInstance(instanceChange.getChangeType())
                .build();
    }


    public WishListDto toDto(WishList WL) {
        return new WishListDto.Builder()
                .setId(WL.getId())
                .setName(WL.getName())
                .setDescription(WL.getDescription())
                .setPlaceId(WL.getPlace())
                .setItems(WL.getItems().stream().map(this::toDto).collect(Collectors.toList()))
                .setStatus(WL.getStatus())
                .build();
    }


    public WishListItemDto toDto(WishListItem WLI) {
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


    public ItemDto toDto(Item item) {
        return new ItemDto.Builder()
                .setId(item)
                .setName(item)
                .setBarcode(item)
                .setPlaceId(item.place)
                .setCategoryId(item.category)
                .setProducer(toDto(item.producer))
                .setDescription(item)
                .setStorage(item)
                .setCapacity(item)
                .setAllergens(item.allergens.entrySet().stream().map(this::toDto).collect(Collectors.toList()))
                .setIngredients(item.ingredients.stream().map(this::toDto).collect(Collectors.toList()))
                .setNutrition(toDto(item.nutrition))
                .build();
    }


    public ProducerDto toDto(Producer producer) {
        return new ProducerDto.Builder()
                .setId(producer)
                .setName(producer.getName())
                .build();
    }


    public NutritionDto toDto(Nutrition N) {
        return new NutritionDto.Builder()
                .setId(N)
                .setEnergy(N.getEnergy_kj(), N.getEnergy_kcal())
                .setFat(N.getFat())
                .setSaturatedFat(N.getSaturatedFat())
                .setCarbohydrate(N.getCarbohydrate())
                .setSugar(N.getSugar())
                .setProtein(N.getProtein())
                .setSalt(N.getSalt())
                .build();
    }


    public AllergenDto toDto(Map.Entry<Allergen, Boolean> a) {
        return new AllergenDto.Builder()
                .setId(a.getKey().getId())
                .setName(a.getKey().getName())
                .doContains(a.getValue())
                .build();
    }


    public IngredientDto toDto(Ingredient ingredient) {
        return new IngredientDto.Builder()
                .setId(ingredient)
                .setName(ingredient.getName())
                .build();
    }

}
