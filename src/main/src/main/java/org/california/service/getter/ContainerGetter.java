package org.california.service.getter;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.repository.container.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ContainerGetter {


    private ContainerRepository containerRepository;

    @Autowired
    public ContainerGetter(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }


    public Container getById(Long id) {
        if(id == null)
            return null;

        return containerRepository.getByKey(id);
    }


    public Collection<Container> getByIds(Collection<Long> ids) {
        return containerRepository.getByIds(ids);
    }


    public Collection<Container> getByPlaces(Collection<Place> places) {
        Collection<Container> result = new ArrayList<>();

        for(Place place : places)
            result.addAll(place.getContainers());

        return result;
    }


    public Collection<Container> getByAccounts(Collection<Account> accounts) {
        Collection<Container> result = new ArrayList<>();

        for(Account account : accounts)
            result.addAll(getByPlaces(account.getPlaces()));

        return result;
    }


}
