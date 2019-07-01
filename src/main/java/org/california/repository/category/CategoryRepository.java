package org.california.repository.category;

import org.california.model.entity.item.Category;
import org.california.repository.AbstractNamedEntityRepository;
import org.california.service.getter.ItemInstanceGetter;

import java.util.Collection;

public interface CategoryRepository extends AbstractNamedEntityRepository<Category> {

    Category getByName(String name);

    Collection<Category> getByIds(Collection<Long> ids);

    Collection<Category> getByParent(Category o);
}
