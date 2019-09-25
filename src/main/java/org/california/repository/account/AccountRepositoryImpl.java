package org.california.repository.account;

import org.california.model.entity.Account;
import org.california.repository.AbstractNamedEntityRepositoryImpl;
import org.california.repository.Repositories;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class AccountRepositoryImpl extends AbstractNamedEntityRepositoryImpl<Account> implements AccountRepository {


    public AccountRepositoryImpl() {
        this.setClazz(Account.class);
    }

    @Transactional(readOnly = true)
    public Account getByEmail(String email) {

        final String HQL = "SELECT A FROM Account A WHERE A.email = :email";

        Query query = getSession().createQuery(HQL);
        query.setParameter("email", email);

        try {
            return (Account) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


}
