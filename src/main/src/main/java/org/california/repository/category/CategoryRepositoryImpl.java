package org.california.repository.category;

import org.california.model.entity.Category;
import org.california.repository.AbstractNamedEntityRepositoryImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class CategoryRepositoryImpl extends AbstractNamedEntityRepositoryImpl<Category> implements CategoryRepository {

    public CategoryRepositoryImpl() {
        setClazz(Category.class);
    }

    public Category getByName(String name) {

        final String HQL = "SELECT I from Category I WHERE I.name = :name";
        Query<Category> query = getSession().createQuery(HQL);
        query.setParameter("name", name);

        return query.getSingleResult();
    }

    @Override
    public Collection<Category> getByIds(Collection<Long> ids) {

        final String HQL = "SELECT C FROM Category C WHERE C.id in (:ids)";

        Query<Category> query = getSession().createQuery(HQL);
        query.setParameterList("ids", ids);

        return query.getResultList();
    }


}
