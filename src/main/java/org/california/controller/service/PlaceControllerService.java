package org.california.controller.service;

import org.california.controller.service.utils.Utils;
import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.model.transfer.request.PlaceForm;
import org.california.model.transfer.response.EntityToDtoMapper;
import org.california.model.transfer.response.PlaceDto;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.PlaceService;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PlaceControllerService {

    private GetterService getterService;
    private PlaceService placeService;
    private EntityToDtoMapper mapper;
    private AccountPermissionsService accountPermissionsService;


    @Autowired
    public PlaceControllerService(GetterService getterService, PlaceService placeService, EntityToDtoMapper mapper, AccountPermissionsService accountPermissionsService) {
        this.getterService = getterService;
        this.placeService = placeService;
        this.mapper = mapper;
        this.accountPermissionsService = accountPermissionsService;
    }


    public PlaceDto newPlace(String token, PlaceForm placeForm) {

        Account account = getterService.accounts.getByToken(token);

        Place place = placeService.newPlace(account, placeForm);
        return mapper.placeToDto(place);
    }


    public Collection<PlaceDto> get(String token, String placeIdsString) {
        Account account = getterService.accounts.getByToken(token);
        Collection<Long> placeIds = Utils.collectionOf(placeIdsString);

        Collection<Place> places = getterService.places.getByIds(placeIds);

        return places.stream()
                            .filter(p -> accountPermissionsService.hasAccessToPlace(account, p))
                            .map(mapper::placeToDto)
                            .collect(Collectors.toList());
    }


    public Boolean removeUserFromPlace(String token, Long placeId, Long userId) {

        Account account = getterService.accounts.getByToken(token);
        Place place = getterService.places.getByKey(placeId);
        Account user = getterService.accounts.getByKey(userId);

        if(!accountPermissionsService.isAdminOfPlace(account, place) && !account.equals(user))
            throw new UnauthorizedException();

        return placeService.removeUser(place, user);
    }


    public Boolean addUserToPlace(String token, Long placeId, Long userId) {

        Account account = getterService.accounts.getByToken(token);
        Place place = getterService.places.getByKey(placeId);
        Account user = getterService.accounts.getByKey(userId);

        if(!accountPermissionsService.isAdminOfPlace(account, place))
            throw new UnauthorizedException();

        return placeService.newUser(place, user);
    }


    public Boolean changeAdmin(String token, Long placeId, Long newAdminId) {

        Account account = getterService.accounts.getByToken(token);
        Place place = getterService.places.getByKey(placeId);
        Account newAdmin = getterService.accounts.getByKey(newAdminId);

        if(!accountPermissionsService.isAdminOfPlace(account, place))
            throw new UnauthorizedException();

        return placeService.changeAdmin(place, newAdmin);
    }

}
