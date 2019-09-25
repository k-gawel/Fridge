package org.california.repository.item;

import org.california.model.entity.item.Allergen;
import org.california.repository.AbstractNamedEntityRepositoryImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class AllergenRepositoryImpl extends AbstractNamedEntityRepositoryImpl<Allergen> implements AllergenRepository {

    public AllergenRepositoryImpl() {
        setClazz(Allergen.class);
    }

    @Override
    public Optional<Allergen> getByNameAndValue(String name, boolean contains) {

        final String HQL = "SELECT A FROM Allergen A WHERE A.name = :name AND A.contains = :contains";

        Query<Allergen> query = getSession().createQuery(HQL);
        query.setParameter("name", name);
        query.setParameter("contains", contains);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
