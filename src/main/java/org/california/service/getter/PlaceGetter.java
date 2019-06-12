package org.california.service.getter;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.repository.place.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class PlaceGetter {

    private final PlaceRepository placeRepository;

    @Autowired
    PlaceGetter(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    public Place getByKey(Serializable key) {
        return placeRepository.getByKey(key);
    }


    @NotEmpty
    public Collection<Place> getByIds(Collection<Long> ids) {
        return CollectionUtils.isEmpty(ids) ?
                Collections.emptySet() : placeRepository.getByIds(ids);
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
