package org.california.service.getter;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.repository.place.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class PlaceGetter {

    private PlaceRepository placeRepository;

    @Autowired
    public PlaceGetter(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    public Place getByKey(Serializable key) {
        return placeRepository.getByKey(key);
    }


    public Collection<Place> getByIds(Collection<Long> ids) {
        if(CollectionUtils.isEmpty(ids))
            return Collections.emptySet();

        return placeRepository.getByIds(ids);
    }


    public Collection<Place> getByAccounts(Collection<Account> accounts) {
        Collection<Place> result = new ArrayList<>();

        for(Account account : accounts)
            result.addAll(account.getPlaces());

        return result;
    }


    public Collection<Place> getByContainers(Collection<Container> containers) {
        Collection<Place> result = new ArrayList<>();

        for(Container container : containers)
            result.add(container.getPlace());

        return result;
    }

}
