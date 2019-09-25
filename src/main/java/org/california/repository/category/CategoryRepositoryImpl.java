package org.california.repository.category;

import org.california.model.entity.item.Category;
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


    @Override
    public Collection<Category> getByIds(Collection<Long> ids) {
        final String HQL = "SELECT C FROM Category C WHERE C.id in (:ids)";

        Query<Category> query = getSession().createQuery(HQL);
        query.setParameterList("ids", ids);

        return query.getResultList();
    }

    @Override
    public Collection<Category> getByParent(Category parent) {
        Query query = getSession().createQuery(parent != null ?
                "SELECT C FROM Category C WHERE C.parent = :parent" :
                "SELECT C FROM Category C WHERE C.parent IS NULL");
        if (parent != null)
            query.setParameter("parent", parent);

        return query.getResultList();
    }


}
