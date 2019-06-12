package org.california.repository.account;

import org.california.model.entity.Account;
import org.california.repository.AbstractNamedEntityRepository;

import java.util.Collection;

public interface AccountRepository extends AbstractNamedEntityRepository<Account> {

    Account getByName(String name);
    Account getByEmail(String email);
    Collection<Account> getByIds(Collection<Long> ids);

}
