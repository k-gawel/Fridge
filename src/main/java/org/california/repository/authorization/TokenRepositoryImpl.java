package org.california.repository.authorization;

import org.california.model.entity.Account;
import org.california.model.entity.Token;
import org.california.repository.AbstractRepositoryImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class TokenRepositoryImpl extends AbstractRepositoryImpl<Token> implements TokenRepository {

    public TokenRepositoryImpl() {
        setClazz(Token.class);
    }

    @Transactional(readOnly = true)
    public Token getByAccount(Account account) {
        if(account == null)
            return null;

        final String HQL = "SELECT T FROM Token T WHERE T.account = :account";

        Query<Token> query = getSession().createQuery(HQL);
        query.setParameter("account", account);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Token getByToken(String token) {
        if(token == null)
            return null;

        final String HQL = "SELECT T FROM Token T WHERE T.token = :token";

        Query<Token> query = getSession().createQuery(HQL);
        query.setParameter("token", token);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
