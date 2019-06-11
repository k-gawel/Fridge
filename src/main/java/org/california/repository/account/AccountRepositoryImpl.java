package org.california.repository.account;

import org.california.model.entity.Account;
import org.california.repository.AbstractNamedEntityRepositoryImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Repository
public class AccountRepositoryImpl extends AbstractNamedEntityRepositoryImpl<Account> implements AccountRepository {


    public AccountRepositoryImpl() {
        this.setClazz(Account.class);
    }

    @Transactional(readOnly = true)
    public Account getByName(String name) {

        String HQL = "SELECT A FROM Account A WHERE A.name = :name";

        Query<Account> query = getSession().createQuery(HQL);
        query.setParameter("name", name);

        try {
            Account result =  query.getSingleResult();
            return result;
        } catch (Exception e) {
            return null;
        }

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


    @Override
    public Collection<Account> getByIds(Collection<Long> ids) {
        if(ids == null || ids.isEmpty())
            return Collections.emptySet();

        final String HQL = "SELECT A FROM Account A WHERE id IN (:ids)";

        Query<Account> query = getSession().createQuery(HQL);
        query.setParameterList("ids", ids);

        return query.getResultList();
    }

}
