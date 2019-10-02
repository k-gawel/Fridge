package org.california.service.builders;

import org.california.model.entity.*;
import org.california.model.entity.item.*;
import org.california.model.entity.utils.AccountDate;
import org.california.model.transfer.response.AccountDateDto;
import org.california.model.transfer.response.BaseDto;
import org.california.model.transfer.response.NamedEntityDto;
import org.california.model.transfer.response.item.*;
import org.california.model.transfer.response.iteminstance.InstanceChangeDto;
import org.california.model.transfer.response.iteminstance.ItemInstanceDto;
import org.california.model.transfer.response.iteminstance.MoneyDto;
import org.california.model.transfer.response.place.*;
import org.california.service.getter.GetterService;
import org.jetbrains.annotations.Contract;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Supplier;
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
        return new NamedEntityDto.Builder().create()
                .withId(namedEntity.getId())
                .withName(namedEntity.getName())
                .build();
    }


    public PlaceDto toDto(Place place) {
        Collection<WishList> wishLists = getter.wishLists.get(Collections.singleton(place), true);
        Collection<ShopList> shopLists = getter.shopLists.get(Collections.singleton(place), true);
        var deletedInstancesFromWishLists = getDeletedInstancesDtoFromWishLists(wishLists);
        var deletedInstancesFromShopLists = getDeletedInstanceDtosFromShopLists(shopLists);

        Collection<ContainerDto> containerDtos = place.getContainers().stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        addInstancesDtosToContainersDtos(containerDtos, deletedInstancesFromWishLists);
        addInstancesDtosToContainersDtos(containerDtos, deletedInstancesFromShopLists);

        Collection<PlaceUserDto> placeUsers = placeUsersToDto(place);
        var wishListDtos = wishLists.stream().map(this::toDto).collect(Collectors.toSet());
        var shopListDtos = shopLists.stream().map(this::toDto).collect(Collectors.toSet());

        return new PlaceDto.Builder().create()
                .withId(place.getId())
                .withName(place.getName())
                .withAdminId(place.getAdmin().getId())
                .withContainers(containerDtos)
                .withUsers(placeUsers)
                .withWishLists(wishListDtos)
                .withShopLists(shopListDtos)
                .withLogs(getLogs(place))
                .build();
    }


    private List<InstanceChangeDto> getLogs(Place place) {
        return getter.instanceLogs.get(Collections.singleton(place), 20, 0)
                                  .stream().map(this::toDto).collect(Collectors.toList());
    }


    private void addInstancesDtosToContainersDtos(Collection<ContainerDto> containerDtos, Collection<ItemInstanceDto> instanceDtos) {
        for (ContainerDto containerDto : containerDtos) {
            long id = containerDto.getId();
            instanceDtos.stream().filter(ii -> ii.getContainerId() == id).forEach(containerDto.getInstances()::add);
        }
    }


    private Collection<ItemInstanceDto> getDeletedInstancesDtoFromWishLists(Collection<WishList> wishLists) {
        return wishLists.stream()
                .flatMap(w -> w.getItems().stream())
                .map(WishListItem::getAddedInstance)
                .filter(Objects::nonNull)
                .filter(ItemInstance::isDeleted)
                .map(this::toDto)
                .collect(Collectors.toSet());
    }


    private Collection<ItemInstanceDto> getDeletedInstanceDtosFromShopLists(Collection<ShopList> shopLists) {
        return shopLists.stream()
                .flatMap(s -> s.getInstances().stream())
                .filter(ItemInstance::isDeleted)
                .map(this::toDto)
                .collect(Collectors.toSet());
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
        var instances = getUndeletedInstanceDtos(container);

        return new ContainerDto.Builder().create()
                .withId(container.getId())
                .withName(container.getName())
                .withPlace_id(container.getPlace().getId())
                .withInstances(instances)
                .build();
    }


    private Collection<ItemInstanceDto> getUndeletedInstanceDtos(Container container) {
        return container.getItemInstances().stream()
                .filter(ii -> !ii.isDeleted())
                .map(this::toDto)
                .collect(Collectors.toSet());
    }


    private PlaceUserDto placeUserToDto(Account account, Place place, boolean status) {
        return new PlaceUserDto.Builder().create()
                .withId(account.getId())
                .withName(account.getName())
                .withStatus(status)
                .withStats(getter.placeUserStats.getStats(account, place))
                .build();
    }


    public ItemInstanceDto toDto(ItemInstance ii) {
        return new ItemInstanceDto.Builder().create()
                .withId(ii.getId())
                .withComment(ii.getComment())
                .withPrice(toDto(ii.getPrice()))
                .withExpireOn(ii.getExpireOn())
                .withItemId(ii.getItem().getId())
                .withContainerId(ii.getContainer().getId())
                .withAdded(toDto(ii.getAdded()))
                .withOpened(toDto(ii.getOpened()))
                .withFrozen(toDto(ii.getFrozened()))
                .withDeleted(toDto(ii.getDeleted()))
                .build();
    }


    public InstanceChangeDto toDto(InstanceChange ic) {
        return new InstanceChangeDto.Builder().create()
                .withId(ic.getId())
                .withInstance(toDto(ic.getInstance()))
                .withChanged(toDto(ic.getChanged()))
                .withChangeType(ic.getChangeType())
                .build();
    }


    public WishListDto toDto(WishList WL) {
        return new WishListDto.Builder().create()
                .withId(WL.getId())
                .withPlaceId(WL.getPlace().getId())
                .withStatus(WL.isStatus())
                .withCreated(toDto(WL.getCreated()))
                .withArchived(toDto(WL.getArchived()))
                .withName(WL.getName())
                .withDescription(WL.getDescription())
                .withItems(WL.getItems().stream().map(this::toDto).collect(Collectors.toList()))
                .build();
    }


    @Contract("null -> null")
    public WishListItemDto toDto(WishListItem WLI) {
        if (WLI == null) return null;

        Long addedInstanceId = nullable(() -> WLI.getAddedInstance().getId());

        return new WishListItemDto.Builder().create()
                .withId(WLI.getId())
                .withWishListId(WLI.getWishList().getId())
                .withCreated(toDto(WLI.getCreated()))
                .withCategoryId(WLI.getCategory().getId())
                .withComment(WLI.getComment())
                .withAdded(toDto(WLI.getAdded()))
                .withAddedInstanceId(addedInstanceId)
                .build();
    }


    @Contract("null -> null")
    public ItemDto toDto(Item item) {
        if (item == null) return null;
        Long placeId = item.getPlace() != null ? item.getPlace().getId() : null;

        return new ItemDto.Builder().create()
                .withId(item.getId())
                .withName(item.getName())
                .withBarcode(item.getBarcode())
                .withPlaceId(placeId)
                .withCategoryId(item.getCategory().getId())
                .withProducer(toDto(item.getProducer()))
                .withDescription(item.getDescription())
                .withStorage(item.getStorage())
                .withCapacity(toDto(item.getCapacity()))
                .withAllergens(item.allergens.entrySet().stream().map(this::toDto).collect(Collectors.toList()))
                .withIngredients(item.ingredients.stream().map(this::toDto).collect(Collectors.toList()))
                .withNutrition(toDto(item.nutrition))
                .build();
    }


    @Contract("null -> null")
    public ProducerDto toDto(Producer producer) {
        if (producer == null) return null;

        return new ProducerDto.Builder().create()
                .withId(producer.getId())
                .withName(producer.getName())
                .build();
    }


    @Contract("null -> null")
    public NutritionDto toDto(Nutrition N) {
        if (N == null) return null;

        return new NutritionDto.Builder().create()
                .withId(N.getId())
                .withEnergyKj(N.getEnergy_kj())
                .withEnergyKcal(N.getEnergy_kcal())
                .withFat(N.getFat())
                .withSaturatedFat(N.getSaturatedFat())
                .withCarbohydrate(N.getCarbohydrate())
                .withSugar(N.getSugar())
                .withProtein(N.getProtein())
                .withSalt(N.getSalt())
                .build();
    }


    @Contract("null -> null")
    public AllergenDto toDto(Map.Entry<Allergen, Boolean> a) {
        if (a == null) return null;

        return new AllergenDto.Builder().create()
                .withId(a.getKey().getId())
                .withName(a.getKey().getName())
                .withContains(a.getValue())
                .build();
    }


    @Contract("null -> null")
    public IngredientDto toDto(Ingredient ingredient) {
        if (ingredient == null) return null;

        return new IngredientDto.Builder().create()
                .withId(ingredient.getId())
                .withName(ingredient.getName())
                .build();
    }

    @Contract("null -> null")
    public String toDto(Capacity capacity) {
        return capacity == null ? null : capacity.toString();
    }


    @Contract("null -> null")
    public MoneyDto toDto(Money money) {
        if (money == null) return null;

        return new MoneyDto.Builder().create()
                .withAmount(money.getAmount())
                .withCurrency(money.getCurrencyUnit().toString())
                .build();
    }


    @Contract("null -> null")
    public AccountDateDto toDto(AccountDate ad) {
        if (ad == null) return null;

        return new AccountDateDto.Builder().create()
                .withAccount(ad.getAccount().getId())
                .withDate(ad.getDate())
                .build();
    }


    public ShopListDto toDto(ShopList sl) {
        return new ShopListDto.Builder().create()
                .withId(sl.getId())
                .withPlaceId(sl.getPlace().getId())
                .withCreated(toDto(sl.getCreated()))
                .withStatus(sl.isStatus())
                .withDescription(sl.getDescription())
                .withShopName(sl.getShopName())
                .withInstances(sl.getInstances().stream().map(BaseEntity::getId).collect(Collectors.toList()))
                .build();
    }


    private static <T> T nullable(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NullPointerException e) {
            return null;
        }
    }


    public <T extends BaseEntity> BaseDto<T> toDto(T t) {
        Method mapperMethod = Arrays.stream(getClass().getDeclaredMethods())
                                    .filter(m -> m.getName().equals("toDto"))
                                    .filter(m -> m.getParameterCount() == 1)
                                    .filter(m -> m.getParameterTypes()[0].equals(t.getClass()))
                                    .findFirst().orElseThrow(() -> new IllegalArgumentException("Cannot find mapper method for " + t.getClass()));

        try {
            return (BaseDto<T>) mapperMethod.invoke(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Cannot invoke  mapper method for " + t.getClass(), e);
        }
    }


}
