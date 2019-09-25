package org.california.service.getter;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.repository.place.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PlaceGetter extends BaseGetter<Place> {

    private final PlaceRepository placeRepository;

    @Autowired
    PlaceGetter(PlaceRepository placeRepository) {
        super(placeRepository, Place.class);
        this.placeRepository = placeRepository;
    }


    public Collection<Place> getByAccounts(Collection<Account> accounts) {
        return accounts.stream()
                .map(Account::getPlaces)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }


    public Collection<Place> getByContainers(Collection<Container> containers) {
        return containers.stream()
                .map(Container::getPlace)
                .collect(Collectors.toSet());
    }

}
