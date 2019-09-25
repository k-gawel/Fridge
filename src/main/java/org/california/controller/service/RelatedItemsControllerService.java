package org.california.controller.service;

import org.california.controller.service.utils.Utils;
import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.model.transfer.response.item.ItemDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.RelatedItemsService;
import org.california.util.enums.RelatedItemsType;
import org.california.util.exceptions.NotValidException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class RelatedItemsControllerService {

    private final GetterService getterService;
    private final RelatedItemsService relatedItemsService;
    private final AccountPermissionsService accountPermissionsService;
    private final EntityToDtoMapper mapper;

    public RelatedItemsControllerService(GetterService getterService, RelatedItemsService relatedItemsService, AccountPermissionsService accountPermissionsService, EntityToDtoMapper mapper) {
        this.getterService = getterService;
        this.relatedItemsService = relatedItemsService;
        this.accountPermissionsService = accountPermissionsService;
        this.mapper = mapper;
    }

    public Collection<ItemDto> get(String token, String placeIdsString, Long categoryId, String param) {

        Account account = getterService.accounts.getByToken(token);
        Collection<Number> placeIds = Utils.collectionOf(placeIdsString);
        RelatedItemsType type = RelatedItemsType.of(param);
        Category category = getterService.categories.getByKeyOrThrow(categoryId);

        Collection<Place> places = getterService.places.getByKeys(placeIds);

        Collection<Item> result;

        switch (type) {
            case MOST_POPULAR:
                result = relatedItemsService.getMostPopular(category, places);
                break;
            case ALL:
                result = getterService.items.searchByPlaceAndCategories(places, Collections.singleton(category));
                break;
            default:
                throw new NotValidException("params.not_valid");
        }

        return result.stream()
                .filter(i -> accountPermissionsService.hasAccessToItem(account, i))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
