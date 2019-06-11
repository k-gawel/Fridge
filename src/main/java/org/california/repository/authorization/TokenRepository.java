package org.california.repository.authorization;

import org.california.model.entity.Account;
import org.california.model.entity.Token;
import org.california.repository.AbstractRepository;

public interface TokenRepository extends AbstractRepository<Token> {

    Token getByToken(String token);
    Token getByAccount(Account account);

}
