package org.california.controller.service;

import org.california.controller.service.utils.Utils;
import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.model.transfer.request.forms.PlaceForm;
import org.california.model.transfer.response.place.PlaceDto;
import org.california.service.builders.EntityToDtoMapper;
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
        return mapper.toDto(place);
    }


    public Collection<PlaceDto> get(String token, String placeIdsString) {
        Account account = getterService.accounts.getByToken(token);
        Collection<Number> placeIds = Utils.collectionOf(placeIdsString);

        Collection<Place> places = getterService.places.getByKeys(placeIds);

        return places.stream()
                            .filter(p -> accountPermissionsService.hasAccessToPlace(account, p))
                            .map(mapper::toDto)
                            .collect(Collectors.toList());
    }


    public Boolean removeUserFromPlace(String token, Long placeId, Long userId) {

        Account account = getterService.accounts.getByToken(token);
        Place place = getterService.places.getByKeyOrThrow(placeId);
        Account user = getterService.accounts.getByKeyOrThrow(userId);

        if(!accountPermissionsService.isAdminOfPlace(account, place) && !account.equals(user))
            throw new UnauthorizedException();

        return placeService.removeUser(place, user);
    }


    public Boolean addUserToPlace(String token, Long placeId, Long userId) {

        Account account = getterService.accounts.getByToken(token);
        Place place = getterService.places.getByKeyOrThrow(placeId);
        Account user = getterService.accounts.getByKeyOrThrow(userId);

        if(!accountPermissionsService.isAdminOfPlace(account, place))
            throw new UnauthorizedException();

        return placeService.newUser(place, user);
    }


    public Boolean changeAdmin(String token, Long placeId, Long newAdminId) {

        Account account = getterService.accounts.getByToken(token);
        Place place = getterService.places.getByKeyOrThrow(placeId);
        Account newAdmin = getterService.accounts.getByKeyOrThrow(newAdminId);

        if(!accountPermissionsService.isAdminOfPlace(account, place))
            throw new UnauthorizedException();

        return placeService.changeAdmin(place, newAdmin);
    }

}
