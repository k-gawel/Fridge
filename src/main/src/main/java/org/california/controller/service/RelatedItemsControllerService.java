package org.california.controller.service;

import org.california.controller.service.utils.Utils;
import org.california.model.entity.Account;
import org.california.model.entity.Category;
import org.california.model.entity.Item;
import org.california.model.entity.Place;
import org.california.model.transfer.response.EntityToDtoMapper;
import org.california.model.transfer.response.ItemDto;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.RelatedItemsService;
import org.california.util.enums.RelatedItemsType;
import org.california.util.exceptions.NotValidException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RelatedItemsControllerService {

    private GetterService getterService;
    private RelatedItemsService relatedItemsService;
    private AccountPermissionsService accountPermissionsService;
    private EntityToDtoMapper mapper;

    public RelatedItemsControllerService(GetterService getterService, RelatedItemsService relatedItemsService, AccountPermissionsService accountPermissionsService, EntityToDtoMapper mapper) {
        this.getterService = getterService;
        this.relatedItemsService = relatedItemsService;
        this.accountPermissionsService = accountPermissionsService;
        this.mapper = mapper;
    }

    public Collection<ItemDto> get(String token, String placeIdsString, Long categoryId, String param) {

        Account account = getterService.accounts.getByToken(token);
        Collection<Long> placeIds = Utils.collectionOf(placeIdsString);
        RelatedItemsType type = RelatedItemsType.of(param);
        Category category = getterService.categories.getByKey(categoryId);

        Collection<Place> places = getterService.places.getByIds(placeIds);

        Collection<Item> result;

        switch (type) {
            case MOST_POPULAR:
                result = relatedItemsService.getMostPopular(category, places);
                break;
            default:
                throw new NotValidException("params.not_valid");
        }

        return result.stream()
                .filter(i -> accountPermissionsService.hasAccessToItem(account, i))
                .map(mapper::itemToDto)
                .collect(Collectors.toList());
    }

}
