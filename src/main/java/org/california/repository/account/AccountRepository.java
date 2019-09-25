package org.california.repository.account;

import org.california.model.entity.Account;
import org.california.repository.AbstractNamedEntityRepository;

public interface AccountRepository extends AbstractNamedEntityRepository<Account> {

    Account getByEmail(String email);

}
