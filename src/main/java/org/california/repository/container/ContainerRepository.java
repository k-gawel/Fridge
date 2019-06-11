package org.california.repository.container;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.util.KeyValue;
import org.california.repository.AbstractRepository;

import java.util.Collection;

public interface ContainerRepository extends AbstractRepository<Container> {

    Collection<Container> getByIds(Collection<Long> ids);
    Collection<KeyValue<Account, Long>> getAddedInstancesStats(Collection<Account> accounts, Collection<Container> containers);
    Collection<KeyValue<Account, Long>> getOpenedInstancesStats(Collection<Account> accounts, Collection<Container> containers);
    Collection<KeyValue<Account, Long>> getDeletedInstancesStats(Collection<Account> accounts, Collection<Container> containers);
}
