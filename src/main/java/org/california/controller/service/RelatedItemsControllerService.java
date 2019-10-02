package org.california.controller.service;

import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.model.transfer.response.item.ItemDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.RelatedItemsService;
import org.california.util.StringUtils;
import org.california.util.enums.RelatedItemsType;
import org.california.util.exceptions.NotValidException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class RelatedItemsControllerService extends BaseControllerService {

    private final RelatedItemsService relatedItemsService;

    public RelatedItemsControllerService(GetterService getter, RelatedItemsService relatedItemsService, AccountPermissionsService permissions, EntityToDtoMapper mapper) {
        super(getter, mapper, permissions);
        this.relatedItemsService = relatedItemsService;
    }

    @SuppressWarnings("unchecked")
    public Collection<ItemDto> get(String token, String placeIdsString, Long categoryId, String param) {
        var account = getter.accounts.getByToken(token);
        var places = StringUtils.collectionOf(placeIdsString).stream()
                                                       .map(getter.places::getByKeyOrThrow)
                                                       .collect(Collectors.toSet());
        Category category = getter.categories.getByKeyOrThrow(categoryId);
        RelatedItemsType type = RelatedItemsType.of(param);

        Collection<Item> result;

        switch (type) {
            case MOST_POPULAR:
                result = relatedItemsService.getMostPopular(category, places);
                break;
            case ALL:
                result = getter.items.searchByPlaceAndCategories(places, Collections.singleton(category));
                break;
            default:
                throw new NotValidException("params.notValid");
        }

        return filerAndMap(result, account);
    }

}
