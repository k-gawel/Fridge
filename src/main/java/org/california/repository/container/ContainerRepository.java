package org.california.repository.container;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.repository.AbstractRepository;

import java.util.Collection;
import java.util.Map;

public interface ContainerRepository extends AbstractRepository<Container> {

    Collection<Container> getByIds(Collection<Long> ids);
    Map<Account, Long> getAddedInstancesStats(Collection<Account> accounts, Collection<Container> containers);
    Map<Account, Long> getOpenedInstancesStats(Collection<Account> accounts, Collection<Container> containers);
    Map<Account, Long> getDeletedInstancesStats(Collection<Account> accounts, Collection<Container> containers);
}
