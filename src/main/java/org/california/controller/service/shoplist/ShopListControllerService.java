package org.california.controller.service.shoplist;


import org.california.controller.service.BaseControllerService;
import org.california.model.entity.Account;
import org.california.model.entity.ShopList;
import org.california.model.transfer.request.forms.ShopListForm;
import org.california.model.transfer.request.queries.ShopListGetQuery;
import org.california.model.transfer.response.place.ShopListDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.ShopListService;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class ShopListControllerService extends BaseControllerService {

    private final ShopListService shopListService;
    private final ShopListGetterControllerService getterController;

    @Autowired
    public ShopListControllerService(ShopListService shopListService, GetterService getter, AccountPermissionsService permissions, EntityToDtoMapper mapper, ShopListGetterControllerService getterController) {
        super(getter, mapper, permissions);
        this.shopListService = shopListService;
        this.getterController = getterController;
    }

    public Serializable get(Account account, ShopListGetQuery query) {
        return getterController.get(account, query);
    }


    public ShopListDto createShopList(Account account, ShopListForm form) {
        if (!permissions.hasAccess(account, form.place))
            throw new UnauthorizedException(account, form.place);

        ShopList shopList = shopListService.createShopList(form);
        return mapper.toDto(shopList);
    }


    public boolean addItemInstanceToShopList(Account account, Long shopListId, Long instanceId) {
        var shopList = getter.shopLists.getByKeyOrThrow(shopListId);
        var instance = getter.itemInstances.getByKeyOrThrow(instanceId);

        if (!permissions.hasAccess(account, shopList))
            throw new UnauthorizedException(account, shopList);
        if (!permissions.hasAccess(account, instance))
            throw new UnauthorizedException(account, instance);

        return shopListService.addInstance(shopList, instance);
    }


    public boolean archiveShopList(Account account, Long shopListId) {
        var shopList = getter.shopLists.getByKeyOrThrow(shopListId);

        if (!permissions.hasAccess(account, shopList))
            throw new UnauthorizedException(account, shopList);

        return shopListService.archive(shopList);
    }


    public boolean deleteInstanceFromShopList(Account account, Long shopListId, Long instanceId) {
        var shopList = getter.shopLists.getByKeyOrThrow(shopListId);
        var instance = getter.itemInstances.getByKeyOrThrow(instanceId);

        if (!permissions.hasAccess(account, shopList))
            throw new UnauthorizedException(account, shopList);
        if (!permissions.hasAccess(account, instance))
            throw new UnauthorizedException(account, instance);

        return shopListService.removeInstance(shopList, instance);
    }


}