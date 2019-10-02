package org.california.controller.service;


import org.california.model.entity.ShopList;
import org.california.model.transfer.request.forms.ShopListForm;
import org.california.model.transfer.response.place.ShopListDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.ShopListService;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopListControllerService {

    private final ShopListService shopListService;
    private final GetterService getter;
    private final AccountPermissionsService permissionsService;
    private final EntityToDtoMapper mapper;

    @Autowired
    public ShopListControllerService(ShopListService shopListService, GetterService getter, AccountPermissionsService permissionsService, EntityToDtoMapper mapper) {
        this.shopListService = shopListService;
        this.getter = getter;
        this.permissionsService = permissionsService;
        this.mapper = mapper;
    }


    public ShopListDto createShopList(String token, ShopListForm form) {
        var account = getter.accounts.getByToken(token);

        if (!permissionsService.hasAccess(account, form.place))
            throw new UnauthorizedException(account, form.place);

        ShopList shopList = shopListService.createShopList(form);
        return mapper.toDto(shopList);
    }


    public boolean addItemInstanceToShopList(String token, Long shopListId, Long instanceId) {
        var account  = getter.accounts.getByToken(token);
        var shopList = getter.shopLists.getByKeyOrThrow(shopListId);
        var instance = getter.itemInstances.getByKeyOrThrow(instanceId);

        if (!permissionsService.hasAccess(account, shopList))
            throw new UnauthorizedException(account, shopList);
        if (!permissionsService.hasAccess(account, instance))
            throw new UnauthorizedException(account, instance);

        return shopListService.addInstance(shopList, instance);
    }


    public boolean archiveShopList(String token, Long shopListId) {
        var account = getter.accounts.getByToken(token);
        var shopList = getter.shopLists.getByKeyOrThrow(shopListId);

        if (!permissionsService.hasAccess(account, shopList))
            throw new UnauthorizedException(account, shopList);

        return shopListService.archive(shopList);
    }


    public boolean deleteInstanceFromShopList(String token, Long shopListId, Long instanceId) {
        var account = getter.accounts.getByToken(token);
        var shopList = getter.shopLists.getByKeyOrThrow(shopListId);
        var instance = getter.itemInstances.getByKeyOrThrow(instanceId);

        if (!permissionsService.hasAccess(account, shopList))
            throw new UnauthorizedException(account, shopList);
        if (!permissionsService.hasAccess(account, instance))
            throw new UnauthorizedException(account, instance);

        return shopListService.removeInstance(shopList, instance);
    }
}