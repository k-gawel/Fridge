package org.california.service.getter;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.model.transfer.response.PlaceUserStats;
import org.california.repository.container.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlaceUserStatsGetter {

    private final ContainerRepository containerRepository;

    @Autowired
    public PlaceUserStatsGetter(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }


    public PlaceUserStats getStats(Account account, Place place) {
        return getStats(Collections.singleton(account), place.getContainers()).stream()
                .findFirst().orElse(new PlaceUserStats(account));
    }


    public PlaceUserStats getStats(Account account, Container container) {
        return getStats(Collections.singleton(account), Collections.singleton(container)).stream()
                .findFirst().orElse(new PlaceUserStats(account));
    }


    public Collection<PlaceUserStats> getStats(Collection<Account> accounts, Collection<Container> containers) {
        Map<Account, Long> added   = containerRepository.getAddedInstancesStats(accounts, containers);
        Map<Account, Long> opened  = containerRepository.getOpenedInstancesStats(accounts, containers);
        Map<Account, Long> deleted = containerRepository.getOpenedInstancesStats(accounts, containers);

        return accounts.stream()
                .map(a -> new PlaceUserStats(a.getId(), added.get(a), opened.get(a), deleted.get(a)))
                .collect(Collectors.toList());
    }

}
