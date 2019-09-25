package org.california.service.getter;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.repository.container.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ContainerGetter extends BaseGetter<Container> {

    private final ContainerRepository containerRepository;

    @Autowired
    ContainerGetter(ContainerRepository containerRepository) {
        super(containerRepository, Container.class);
        this.containerRepository = containerRepository;
    }


    public Collection<Container> getByPlaces(Collection<Place> places) {
        return places.stream()
                .map(Place::getContainers).flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }


    public Collection<Container> getByAccounts(Collection<Account> accounts) {
        return accounts.stream()
                .map(Account::getPlaces).flatMap(Collection::stream)
                .map(Place::getContainers).flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }


}
